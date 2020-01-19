package io.github.sigmaol.bigdata.component.connector.realtime.broadcast

import io.github.sigmaol.bigdata.component.connector.realtime.sinks.{HBaseSink, KafkaSink, MysqlSink}
import org.apache.spark.broadcast.Broadcast


/**
  * 存储各个需要广播的变量
  **/
class BroadcastHouse extends Serializable{
  /**
    * kafka生产者连接器
    **/
  var kafkaProducer: Broadcast[KafkaSink[String, String]] = _

  /**
    * hbase连接器
    **/
  var hbaseConnection: Broadcast[HBaseSink] = _

  /**
    * mysql连接器
    **/
  var mysqlConnection: Broadcast[MysqlSink] = _

}
