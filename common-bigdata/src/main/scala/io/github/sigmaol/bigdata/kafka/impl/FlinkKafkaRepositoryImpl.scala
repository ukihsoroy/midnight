package io.github.sigmaol.bigdata.kafka.impl

import java.util.Properties

import io.github.sigmaol.bigdata.kafka.IKafkaRepository
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}

trait FlinkKafkaRepositoryImpl[B] extends IKafkaRepository[StreamExecutionEnvironment, DataStream[B]] {

  protected def transJson2Bean(jsonStream: DataStream[String]): DataStream[B]

  protected def transBean2Json(beanStream: DataStream[B]): DataStream[String]

  override def readStream(implicit env: StreamExecutionEnvironment): DataStream[B] = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS)
    properties.setProperty("group.id", GROUP_ID)

    //flink.partition-discovery.interval-millis动态分区发现
    val consumer = new FlinkKafkaConsumer[String](TOPIC, new SimpleStringSchema(), properties)
    val jsonStream = env.addSource(consumer)
    transJson2Bean(jsonStream)
  }

  override def writeStream(result: DataStream[B])
                          (implicit env: StreamExecutionEnvironment): Unit = {
    val producer = new FlinkKafkaProducer[String](BOOTSTRAP_SERVERS, GROUP_ID, new SimpleStringSchema)
    producer.setWriteTimestampToKafka(true)
    transBean2Json(result).addSink(producer)
  }
}
