package org.ko.example.spark.sql

import org.ko.example.core.sql.ASparkSQLApplication
import org.ko.example.run.SparkApplication

class SparkSQLWordCountExample(args: Array[String]) extends ASparkSQLApplication(args) {
  /**
    * 业务逻辑处理
    */
  override def process(): Unit = {
    val df = spark.read.json("left.json")
    df.show()
  }
}

object SparkSQLWordCountExample extends App {
  SparkApplication.run(classOf[SparkSQLWordCountExample], args)
}
