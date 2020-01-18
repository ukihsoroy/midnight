package io.github.sigmaol.bigdata.common.util


import io.github.sigmaol.bigdata.common.security.MD5Util

import scala.io.Source

object HBaseUtil {
  /**
    * hbase region预分区工具
    *
    * @param filePath    样本文件路径
    * @param numOfSPlits 预分区个数
    **/
  def rowkeySplitedArr(filePath: String, numOfSPlits: Int) = {
    val file = Source.fromFile(filePath).getLines()
    val res = file.map {
      line =>
        val arr = line.split("_")
        val card = arr(0)
        val name = arr(1)
        MD5Util.encrypt32(card) + MD5Util.encrypt32(card)
    }.toList.sorted
    val count = res.length / numOfSPlits
    var str = ""
    for (i <- 0 until numOfSPlits) {
      str += s"\'${res(i * count)}\',"
    }
    println(str.substring(0, str.length - 1))
  }

  def main(args: Array[String]): Unit = {
    println(rowkeySplitedArr("/Users/xiaohei/Documents/id.txt", 400))
  }
}
