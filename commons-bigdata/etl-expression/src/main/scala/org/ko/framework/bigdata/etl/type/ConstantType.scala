package org.ko.framework.bigdata.etl.`type`

import org.ko.framework.bigdata.etl.TInterpreter

/**
  * 输入的是常量类型
  * @param value
  */
class ConstantType(value: String) extends TInterpreter{
  override def interpret(input: Map[String, String]): String = value
}
object ConstantType {
  def apply(value: String): ConstantType = new ConstantType(value)
}
