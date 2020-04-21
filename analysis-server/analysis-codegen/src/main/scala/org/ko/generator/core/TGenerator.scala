package org.ko.generator.core

trait TGenerator {

  /**
    * 执行生成逻辑
    * @param name 数据库名称
    */
  def executor(name: List[String])

}
