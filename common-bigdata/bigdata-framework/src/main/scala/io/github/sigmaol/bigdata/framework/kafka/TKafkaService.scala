package io.github.sigmaol.bigdata.framework.kafka

import io.github.sigmaol.bigdata.framework.Serviceable

trait TKafkaService[E, R] extends Serviceable with Serializable {

  protected val dao: TKafkaRepository[E, R]

  def select(implicit env: E): R = {
    dao.readStream
  }

  def insert(result: R)(implicit env: E): Unit = {
    dao.writeStream(result)
  }
}

