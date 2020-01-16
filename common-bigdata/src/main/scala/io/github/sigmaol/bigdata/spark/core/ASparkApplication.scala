package io.github.sigmaol.bigdata.spark.core

import ASparkApplication._
import org.apache.spark.SparkConf
import org.slf4j.{Logger, LoggerFactory}

private [core] abstract class ASparkApplication(args: Array[String]) extends TSparkApplication {

  private [core] var userParams: Map[String, String] = _

  private [core] var runParams: Map[String, String] = _

  private [core] var sparkConf: SparkConf = _

  protected val logger: Logger = LoggerFactory.getLogger(this.getClass)

  {
    userParams = Map("user" -> "K.O")
    runParams = Map(
      __ENV_MASTER -> "local[2]",
      __APP_NAME -> "appName"
    )

    val appName = runParams(__APP_NAME)
    val envMaster = runParams(__ENV_MASTER)

    sparkConf = new SparkConf().setAppName(appName).setMaster(envMaster)
  }

  /**
    * 获取用户参数
    * @param key: 用户参数键
    * @param default: 默认值
    * @return
    */
  protected def getParameter (key: String, default: String = ""): String = userParams.getOrElse(key, default)


}

private object ASparkApplication {

  val __ENV_MASTER = "__ENV_MASTER"
  val __APP_NAME = "__APP_NAME"

}
