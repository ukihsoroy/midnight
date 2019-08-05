package org.ko.framework.bigdata.etl.`type`

import org.ko.framework.bigdata.etl.TInterpreter
import org.ko.framework.bigdata.etl.`type`.ArrayType._

class ArrayType(value: String) extends TInterpreter {
  override def interpret(input: Map[String, String]): Array[String] = {
    value.replaceAll(open, replacement).replaceAll(close, replacement).split(separator)
  }
}

object ArrayType {

  val open = "["
  val close = "]"
  val separator = ","
  val replacement = ""

  def apply(value: String): ArrayType = new ArrayType(value)

}
