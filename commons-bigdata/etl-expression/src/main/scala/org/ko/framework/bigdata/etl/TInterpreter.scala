package org.ko.framework.bigdata.etl

import org.apache.commons.lang3.StringUtils
import org.ko.framework.bigdata.etl.`type`.{ArrayType, ColumnType, ConstantType}
import org.ko.framework.bigdata.etl.util.ExtractArgsHelper

trait TInterpreter {
  def interpret(input: Map[String, String]): Any
}

object TInterpreter {

  def parse(expression: String): TInterpreter = {
    var interpreter: TInterpreter = null

    if (StringUtils.isNotBlank(expression)) {

      //定位 ( ) 位置，包含则为函数
      var indexBegin = expression.indexOf("(")
      val indexEnd = expression.lastIndexOf(")")

      if (indexBegin != -1 && indexEnd != -1) {
        //读取函数名称
        val expressionName = expression.substring(0, indexBegin)

        //截取括号内容为函数的参数，对函数参数进行处理
        indexBegin += 1

        interpreter = buildInterpreter(expressionName, parseParams(expression.substring(indexBegin, indexEnd)))
      } else if (expression.contains("'")) {
        //判断是否为常量
        interpreter = ConstantType(expression)
      } else if (expression.contains("[") && expression.contains("]") && expression.contains(",")) {
        //判断是否为数组
        interpreter = ArrayType(expression)
      } else {
        //最后为字段，从输入数据中读取数据
        interpreter = ColumnType(expression)
      }
    }

    interpreter
  }

  private def parseParams(expression: String): Array[TInterpreter] = {
    //函数参数默认为集合列表
    var interpreters: Array[TInterpreter] = null

    //如果表达式不为空
    if (StringUtils.isNotBlank(expression)) {
      //判断是否需要办好组合函数，和拆分
      val expressions = ExtractArgsHelper.parse(expression)

      interpreters = for {
        exp <- expressions
        //对单个函数初始化
        interpreter = parse(exp)
        if interpreter != null
      } yield interpreter //返回最后处理的数据
    }

    //最后的参数数组
    interpreters
  }

  def buildInterpreter(name: String, params: Array[TInterpreter]): TInterpreter = name match {
    case _ => null
  }
}
