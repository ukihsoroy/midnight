package org.ko.generator.core

import java.sql.ResultSet

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import javax.sql.DataSource
import org.apache.commons.lang3.StringUtils
import org.ko.generator.constants.SQLConstants
import org.ko.generator.constants.SQLConstants._
import org.ko.generator.constants.SchemaColumnNameConstants._
import org.ko.generator.entity.Column
import org.ko.generator.util.ConverterSQLTypeHandler
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}

import scala.collection.JavaConverters._

private [core] abstract class AbstractGenerator extends TGenerator {

  val ROOT_DIR = "/src/main/java/"

  val jDBCTemplate = new JdbcTemplate()
  var mysqlDataSource: MysqlDataSource = _

  def dataSource (dataSource: DataSource): Unit = {
    jDBCTemplate.setDataSource(dataSource)
    dataSource match {
      case mds: MysqlDataSource => mysqlDataSource = mds
      case _ => println("###match not case!")
    }
  }

  private [core] def findTableNames(database: String): List[String] = {
    jDBCTemplate.queryForList(INFORMATION_SCHEMA_TABLES, classOf[String], database).asScala.toList
  }


  private [core] def findTableComment (name: String): String = {
    val database = mysqlDataSource.getDatabaseName
    jDBCTemplate.queryForObject(SQLConstants.INFORMATION_SCHEMA_TABLE_DETAIL, classOf[String], database, name)
  }

  private [core] def findColumnByTableName (name: String):  List[Column] = {
    val database = mysqlDataSource.getDatabaseName    
    jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper[Column]() {
      override def mapRow(rs: ResultSet, i: Int): Column= {
        val columnName = rs.getString(COLUMN_NAME)
        val columnType = rs.getString(DATA_TYPE).toLowerCase()
        val charLength = int(rs.getString(CHARACTER_MAXIMUM_LENGTH))
        val precision = int(rs.getString(NUMERIC_PRECISION))
        val scale = int(rs.getString(NUMERIC_SCALE))
        var len = charLength + precision + scale
        if (scale != 0) len = len + 1
        new Column(
          columnName,
          mapUnderscoreToCamelCase(columnName),
          columnType,
          ConverterSQLTypeHandler.format(columnType),
          PRI.equalsIgnoreCase(rs.getString(COLUMN_KEY)),
          len,
          StringUtils.trimToEmpty(rs.getString(COLUMN_COMMENT))
        )
      }
    }, database, name).asScala.toList
  }

  /**
    *
    * @param value
    * @return
    */
  protected def int(value: String): Int = {
    var res = 0
    if (StringUtils.isNotEmpty(value)) res = value.toInt
    res
  }

  protected def mapUnderscoreToCamelCase(name: String): String = {
    val sp = name.split("_")
    val res = new StringBuilder
    res.append(sp.head)
    sp.tail.map(x => x.replaceFirst(x.charAt(0).toString, x.charAt(0).toUpper.toString)).foreach(res.append)
    res.toString()
  }

  protected def reformatTable (name: String, prefix: String): String = {
    var entityName = ""
    if (name.startsWith(prefix)) {
      entityName = mapUnderscoreToCamelCase(name.replaceFirst(prefix, ""))
    } else {
      entityName = mapUnderscoreToCamelCase(name)
    }
    entityName.replaceFirst(entityName.charAt(0).toString, entityName.charAt(0).toUpper.toString)
  }
}
