package io.github.sigmaol.bigdata.kafka.predefined

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.bean.MaxwellBean
import io.github.sigmaol.bigdata.common.util.JavaJsonUtil
import io.github.sigmaol.bigdata.kafka.impl.SparkKafkaRepositoryImpl
import org.apache.spark.streaming.dstream.DStream

abstract class SparkMaxwellKafkaRepositoryImpl extends SparkKafkaRepositoryImpl[MaxwellBean] {

  override protected def transJson2Bean(jsonStream: DStream[String]): DStream[MaxwellBean] = {
    jsonStream.flatMap {
      x =>
        try {
          Some(JSON.parseObject(x, classOf[MaxwellBean]))
        } catch {
          case e: Exception =>
            logger.error(e.getMessage + ", json string:" + x)
            None
        }
    }
  }

  override protected def transBean2Json(beanStream: DStream[MaxwellBean]): DStream[String] = {
    beanStream.map(x => JavaJsonUtil.toJSONString(x))
  }

}
