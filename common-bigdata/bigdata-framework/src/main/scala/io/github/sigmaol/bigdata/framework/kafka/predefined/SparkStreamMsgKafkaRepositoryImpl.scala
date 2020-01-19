package io.github.sigmaol.bigdata.framework.kafka.predefined

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.component.util.JavaJsonUtil
import io.github.sigmaol.bigdata.framework.bean.StreamMsgBean
import io.github.sigmaol.bigdata.framework.kafka.impl.SparkTKafkaRepositoryImpl
import org.apache.spark.streaming.dstream.DStream

abstract class SparkStreamMsgKafkaRepositoryImpl extends SparkTKafkaRepositoryImpl[StreamMsgBean] {

  override protected def transJson2Bean(jsonStream: DStream[String]): DStream[StreamMsgBean] = {
    jsonStream.flatMap {
      x =>
        try {
          Some(JSON.parseObject(x, classOf[StreamMsgBean]))
        } catch {
          case e: Exception =>
            logger.error(e.getMessage + ", json string:" + x)
            None
        }
    }
  }

  override protected def transBean2Json(beanStream: DStream[StreamMsgBean]): DStream[String] = {
    beanStream.map(x => JavaJsonUtil.toJSONString(x))
  }
}
