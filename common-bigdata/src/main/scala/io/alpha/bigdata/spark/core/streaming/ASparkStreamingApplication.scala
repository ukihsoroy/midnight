package io.alpha.bigdata.spark.core.streaming

import io.alpha.bigdata.spark.core.ASparkApplication
import org.apache.spark.SparkContext
import org.apache.spark.streaming.{Seconds, StreamingContext}

abstract class ASparkStreamingApplication(args: Array[String]) extends ASparkApplication(args) {

  protected var sc: SparkContext = _

  protected var ssc: StreamingContext = _

  override def before(): Unit = {
    ssc = new StreamingContext(sparkConf, Seconds(30))
    sc = ssc.sparkContext
  }

  override def exception(exp: Throwable): Unit = {
    println("###exp: " + exp.getMessage)
  }

  override def after(): Unit = {
    ssc.start()
    ssc.awaitTermination()
  }

}
