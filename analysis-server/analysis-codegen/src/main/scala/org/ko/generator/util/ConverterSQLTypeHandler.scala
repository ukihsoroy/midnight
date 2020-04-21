package org.ko.generator.util

import scala.collection.mutable

object ConverterSQLTypeHandler {

  private val container = mutable.Map[String, String]()

  {
    container.put("varchar", "String")
    container.put("char", "String")
    container.put("text", "String")
    container.put("int", "int")
    container.put("tinyint", "byte")
    container.put("smallint", "short")
    container.put("mediumint", "short")
    container.put("bigint", "Long")
    container.put("float", "float")
    container.put("double", "double")
    container.put("decimal", "java.math.BigDecimal")
    container.put("date", "java.util.Date")
    container.put("datetime", "java.util.Date")
    container.put("timestamp", "java.util.Date")
    container.put("json", "String")
  }

  def format(key: String): String = container(key)

}
