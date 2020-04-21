package org.ko.generator.jdbc

import java.sql.ResultSet

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.apache.commons.lang3.StringUtils
import org.junit.{Before, Test}
import org.ko.generator.entity.Column
import org.ko.generator.constants.SQLConstants
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}

import collection.JavaConverters._
import org.ko.generator.constants.SchemaColumnNameConstants._
import org.ko.generator.util.ConverterSQLTypeHandler

@Test
class JDBCTests {

  val jDBCTemplate = new JdbcTemplate()

  @Before
  def before (): Unit = {
    val dataSource = new MysqlDataSource
    dataSource.setDatabaseName("sigma_server")
    dataSource.setPort(3306)
    dataSource.setUser("root")
    dataSource.setPassword("tiger")
    jDBCTemplate.setDataSource(dataSource)
  }

  @Test
  def whenQueryTableSuccess (): Unit = {
    val tables = jDBCTemplate.queryForList(SQLConstants.INFORMATION_SCHEMA_TABLES, classOf[String], "sigma_server").asScala
    tables.foreach(println)
  }

  @Test
  def whenQueryColumnSuccess (): Unit = {
    val columns = jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper[Column]() {
      override def mapRow(rs: ResultSet, i: Int): Column= {
        val columnName = rs.getString(COLUMN_NAME)
        println("###columnName: " + columnName)
        val columnType = rs.getString(DATA_TYPE).toLowerCase()
        println("###columnType: " + columnType)
        val charLength = int(rs.getString(CHARACTER_MAXIMUM_LENGTH))
        println("###charLength: " + charLength)
        val precision = int(rs.getString(NUMERIC_PRECISION))
        println("###precision: " + precision)
        val scale = int(rs.getString(NUMERIC_SCALE))
        println("###scale: " + scale)
        var len = charLength + precision + scale
        println("###len: " + len)
        if (scale != 0) len = len + 1
        println("###len: " + len)
        val comment = StringUtils.trimToEmpty(rs.getString(COLUMN_COMMENT))
        println("###comment: " + comment)
        println("###key: " + PRI.equalsIgnoreCase(rs.getString(COLUMN_KEY)))
        new Column(
          columnName,
          mapUnderscoreToCamelCase(columnName),
          columnType,
          ConverterSQLTypeHandler.format(columnType),
          PRI.equalsIgnoreCase(rs.getString(COLUMN_KEY)),
          len,
          comment
        )
      }
    }, "sigma_server", "t_user")

    println(columns.size())
  }

  @Test
  def whenQueryTableCommonSuccess (): Unit = {
    val common = jDBCTemplate.queryForObject(SQLConstants.INFORMATION_SCHEMA_TABLE_DETAIL, classOf[String], "sigma_server", "t_user")
    println(common)
  }

  @Test
  def whenToUpperSuccess (): Unit = {
    val s = mapUnderscoreToCamelCase("gmt_create")
    println(s)
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

}
