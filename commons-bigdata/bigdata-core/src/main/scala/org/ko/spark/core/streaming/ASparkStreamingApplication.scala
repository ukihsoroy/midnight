package org.ko.spark.core.streaming

import org.apache.spark.SparkContext
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.ko.spark.core.ASparkApplication

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
