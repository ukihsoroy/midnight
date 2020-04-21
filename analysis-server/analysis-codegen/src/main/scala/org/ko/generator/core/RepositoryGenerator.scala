package org.ko.generator.core

import java.io.{File, FileOutputStream, OutputStreamWriter}

import freemarker.template.Configuration
import org.apache.commons.lang3.StringUtils
import org.ko.generator.properties.GeneratorProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import scala.collection.JavaConverters._


/**
  * 实体类生成
  *
  * @param freemarker
  * @param properties
  */
@Component
class RepositoryGenerator @Autowired()(
                                        freemarker: Configuration,
                                        properties: GeneratorProperties
                                      ) extends AbstractGenerator {

  val REPOSITORY_PACKAGE = "/repository/"

  /**
    * 执行生成逻辑
    *
    * @param names 数据库名称
    */
  override def executor(names: List[String]): Unit = {
    names.foreach { name =>
      //表名字
      val entityName = reformatTable(name, properties.prefix)

      //包名称
      val packageName = name.replaceFirst(properties.prefix, "").split("_")(0)

      val params = new java.util.HashMap[String, Object]()
      params.put("name", name)
      params.put("entityName", entityName)
      params.put("rootPackage", properties.backEnd.rootPackage + "." + packageName)

      val dir = new File(this.getClass.getClassLoader.getResource(".").toURI).getAbsolutePath
      val index = dir.indexOf("target")
      val moduleRoot = new File(dir.substring(0, index)).getParent

      val javaDir = (
        moduleRoot + "/"
          + properties.backEnd.module
          + ROOT_DIR + properties.backEnd.rootPackage.replaceAll("\\.", "/") + "/"
          + packageName + REPOSITORY_PACKAGE
        )

      val repositoryFileName = javaDir + entityName + "Repository.java"

      if (StringUtils.isNotEmpty(repositoryFileName)) {
        val dir = new File(javaDir)
        if (!dir.exists()) {
          dir.mkdirs()
        }
        val template = freemarker.getTemplate(properties.backEnd.repositoryTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(repositoryFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }

      //全部字段
      val columns = findColumnByTableName(name)
      params.put("columns", columns.asJavaCollection)

      //表缩写
      val addr = reformatAddr(name, properties.prefix)
      params.put("addr", addr)

      val repositoryXMLFileName = javaDir + entityName + "Repository.xml"

      if (StringUtils.isNotEmpty(repositoryXMLFileName)) {
        val dir = new File(javaDir)
        if (!dir.exists()) {
          dir.mkdirs()
        }
        val template = freemarker.getTemplate(properties.backEnd.repositoryXMLTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(repositoryXMLFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }
    }
  }

  def reformatAddr(name: String, prefix: String): String = {
    val splits = name.replaceFirst(prefix, "").split("_")
    val addr = new StringBuilder()
    splits.foreach(x => addr.append(x.charAt(0)))
    addr.toString()
  }
}
