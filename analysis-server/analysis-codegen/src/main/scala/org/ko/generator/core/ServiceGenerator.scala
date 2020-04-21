package org.ko.generator.core

import java.io.{File, FileOutputStream, OutputStreamWriter}

import freemarker.template.Configuration
import org.apache.commons.lang3.StringUtils
import org.ko.generator.properties.GeneratorProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
  * 实体类生成
  *
  * @param freemarker
  * @param properties
  */
@Component
class ServiceGenerator @Autowired()(
                                        freemarker: Configuration,
                                        properties: GeneratorProperties
                                      ) extends AbstractGenerator {

  val SERVICE_PACKAGE = "/service/"

  val SERVICE_IMPL_PACKAGE = "impl/"


  /**
    * 执行生成逻辑
    *
    * @param names 数据库名称
    */
  override def executor(names: List[String]): Unit = {
    names.foreach { name =>
      //表名字
      val entityName = reformatTable(name, properties.prefix)

      //表注释
      val comment = findTableComment(name)

      //包名称
      val packageName = name.replaceFirst(properties.prefix, "").split("_")(0)

      val dir = new File(this.getClass.getClassLoader.getResource(".").toURI).getAbsolutePath
      val index = dir.indexOf("target")
      val moduleRoot = new File(dir.substring(0, index)).getParent


      val params = new java.util.HashMap[String, Object]()
      params.put("name", name)
      params.put("comment", comment)
      params.put("entityName", entityName)
      params.put("rootPackage", properties.backEnd.rootPackage + "." + packageName)

      val javaDir = (
        moduleRoot + "/"
          + properties.backEnd.module
          + ROOT_DIR + properties.backEnd.rootPackage.replaceAll("\\.", "/") + "/"
          + packageName + SERVICE_PACKAGE
        )

      val serviceFileName = javaDir + entityName + "Service.java"

      if (StringUtils.isNotEmpty(serviceFileName)) {
        val dir = new File(javaDir)
        if (!dir.exists()) {
          dir.mkdirs()
        }
        val template = freemarker.getTemplate(properties.backEnd.serviceTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(serviceFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }

      val serviceImplFileName = javaDir + SERVICE_IMPL_PACKAGE + entityName + "ServiceImpl.java"

      if (StringUtils.isNotEmpty(serviceImplFileName)) {
        val dir = new File(javaDir + SERVICE_IMPL_PACKAGE)
        if (!dir.exists()) {
          dir.mkdirs()
        }
        val template = freemarker.getTemplate(properties.backEnd.serviceImplTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(serviceImplFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }

    }
  }
}
