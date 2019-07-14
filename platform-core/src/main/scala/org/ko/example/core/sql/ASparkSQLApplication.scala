package org.ko.example.core.sql

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.ko.example.core.ASparkApplication

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
