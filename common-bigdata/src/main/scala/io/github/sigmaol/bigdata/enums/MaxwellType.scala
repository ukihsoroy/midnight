package io.github.sigmaol.bigdata.enums

object MaxwellType extends Enumeration {
  type MaxwellTypeEnum = Value
  val INSERT = Value("insert")
  val UPDATE = Value("update")
  val DELETE = Value("delete")
}
