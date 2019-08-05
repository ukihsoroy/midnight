package org.ko.framework.bigdata.etl.`type`

import org.ko.framework.bigdata.etl.TInterpreter

/**
  * 输入的是字段
  */
class ColumnType(column: String) extends TInterpreter {
  override def interpret(input: Map[String, String]): String = input.getOrElse(column, "")
}

object ColumnType {
  def apply(column: String): ColumnType = new ColumnType(column)
}
