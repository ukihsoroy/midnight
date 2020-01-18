package io.github.sigmaol.bigdata.kafka.predefined

import com.alibaba.fastjson.JSON
import io.github.sigmaol.bigdata.bean.StreamMsgBean
import io.github.sigmaol.bigdata.common.util.JavaJsonUtil
import io.github.sigmaol.bigdata.kafka.impl.FlinkKafkaRepositoryImpl
import org.apache.flink.streaming.api.scala._

abstract class FlinkStreamMsgKafkaRepositoryImpl  extends FlinkKafkaRepositoryImpl[StreamMsgBean] {

  override protected def transJson2Bean(jsonStream: DataStream[String]): DataStream[StreamMsgBean] = {
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

  override protected def transBean2Json(beanStream: DataStream[StreamMsgBean]): DataStream[String] = {
    beanStream.map(x => JavaJsonUtil.toJSONString(x))
  }
}
