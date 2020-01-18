package io.github.sigmaol.bigdata.common.util

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.common.constants.TypeMap
import org.apache.spark.sql.SparkSession

//case class TableScheme(fieldName: String, fieldType: String, fieldComment: String)

object HiveUtil {

  def getScheme(spark: SparkSession, tableName: String, toCamel: Boolean): Seq[(String, String, String)] = {
    val schema = spark.table(tableName).schema
    schema.map {
      t =>
        val fieldName = if (toCamel) StringUtil.under2camel4field(t.name) else t.name
        val metaJson = JSON.parseObject(t.metadata.toString)
        val key = metaJson.getString("HIVE_TYPE_STRING")
        val purerKey = if (key.contains("(")) key.split("\\(").head else key
        val fieldType = TypeMap.hive2JavaType.getOrElse(purerKey, "None")
        val fieldComment = if (metaJson.getString("comment") == null) "" else metaJson.getString("comment")
        (fieldName, fieldType, fieldComment)
    }
  }
}
