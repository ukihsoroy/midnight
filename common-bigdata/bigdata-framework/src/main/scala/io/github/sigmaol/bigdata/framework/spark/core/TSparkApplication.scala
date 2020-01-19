package io.github.sigmaol.bigdata.framework.spark.core

trait TSparkApplication extends Serializable {

  /**
    * 前置
    */
  def before()

  /**
    * 业务逻辑处理
    */
  def process()

  /**
    * 异常处理
    * @param exp 异常信息
    */
  def exception(exp: Throwable)

  /**
    * 后置
    */
  def after()

}
