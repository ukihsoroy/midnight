package io.alpha.bigdata.spark.core.sql

import io.alpha.bigdata.spark.core.ASparkApplication
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

abstract class ASparkSQLApplication(args: Array[String]) extends ASparkApplication(args) {

  protected var spark: SparkSession = _

  protected var sc: SparkContext = _

  override def before(): Unit = {
    spark = SparkSession.builder().config(sparkConf).getOrCreate()
    sc = spark.sparkContext
  }

  override def exception(exp: Throwable): Unit = {
    println("###exp: " + exp.getMessage)
  }

  override def after(): Unit = spark.stop()
}
