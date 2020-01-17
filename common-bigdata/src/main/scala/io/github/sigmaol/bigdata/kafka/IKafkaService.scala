package io.github.sigmaol.bigdata.kafka

import io.github.sigmaol.bigdata.IService

trait IKafkaService[E, R] extends IService with Serializable {

  protected val dao: IKafkaRepository[E, R]

  def select(implicit env: E): R = {
    dao.readStream
  }

  def insert(result: R)(implicit env: E): Unit = {
    dao.writeStream(result)
  }
}

