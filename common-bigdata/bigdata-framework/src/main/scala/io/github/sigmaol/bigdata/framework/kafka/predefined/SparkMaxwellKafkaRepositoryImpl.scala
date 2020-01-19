package io.github.sigmaol.bigdata.framework.kafka.predefined

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.component.util.JavaJsonUtil
import io.github.sigmaol.bigdata.framework.bean.MaxwellBean
import io.github.sigmaol.bigdata.framework.kafka.impl.SparkTKafkaRepositoryImpl
import org.apache.spark.streaming.dstream.DStream

abstract class SparkMaxwellKafkaRepositoryImpl extends SparkTKafkaRepositoryImpl[MaxwellBean] {

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
