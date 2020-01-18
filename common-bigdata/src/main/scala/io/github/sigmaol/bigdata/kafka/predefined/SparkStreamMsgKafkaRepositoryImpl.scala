package io.github.sigmaol.bigdata.kafka.predefined

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.bean.StreamMsgBean
import io.github.sigmaol.bigdata.common.util.JavaJsonUtil
import io.github.sigmaol.bigdata.kafka.impl.SparkKafkaRepositoryImpl
import org.apache.spark.streaming.dstream.DStream

/**
  * Author: xiaohei
  * Date: 2019/10/24
  * Email: xiaohei.info@gmail.com
  * Host: xiaohei.info
  */
abstract class SparkStreamMsgKafkaRepositoryImpl extends SparkKafkaRepositoryImpl[StreamMsgBean] {

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
