package io.github.sigmaol.bigdata.kafka.predefined

import io.github.sigmaol.bigdata.bean.MaxwellBean
import io.github.sigmaol.bigdata.kafka.impl.FlinkKafkaRepositoryImpl
import io.github.sigmaol.jackson.JsonHelper
import org.apache.flink.streaming.api.scala._


abstract class FlinkMaxwellKafkaRepositoryImpl extends FlinkKafkaRepositoryImpl[MaxwellBean] {

  override protected def transJson2Bean(jsonStream: DataStream[String]): DataStream[MaxwellBean] = {
    jsonStream.flatMap {
      x =>
        try {
          Some(JsonHelper.parse(x, classOf[MaxwellBean]))
        } catch {
          case e: Exception =>
            logger.error(e.getMessage + ", json string:" + x)
            None
        }
    }
  }

  override protected def transBean2Json(beanStream: DataStream[MaxwellBean]): DataStream[String] = {
    beanStream.map(x => JsonHelper.toJson(x))
  }

}
