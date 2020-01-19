package io.github.sigmaol.bigdata.framework.kafka

import com.typesafe.scalalogging.Logger
import io.github.sigmaol.bigdata.framework.Repositable

trait TKafkaRepository[E, R] extends Repositable with Serializable {

  protected val logger = Logger(this.getClass)

  /**
    * 数据库名
    **/
  val DATABASE: String

  /**
    * 表名
    **/
  val TABLE: String

  /**
    * kafka topic
    **/
  val TOPIC: String

  /**
    * kafka 组名
    **/
  val GROUP_ID: String

  /**
    * kafka集群地址
    **/
  val BOOTSTRAP_SERVERS: String

  def readStream(implicit env: E): R

  def writeStream(result: R)(implicit env: E): Unit
}
