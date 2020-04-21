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
class DTOGenerator @Autowired()(
                                        freemarker: Configuration,
                                        properties: GeneratorProperties
                                      ) extends AbstractGenerator {

  val CONDITION_PACKAGE = "/dto/"

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

      val dir = new File(this.getClass.getClassLoader.getResource(".").toURI).getAbsolutePath
      val index = dir.indexOf("target")
      val moduleRoot = new File(dir.substring(0, index)).getParent

      val javaDir = (
        moduleRoot + "/"
          + properties.backEnd.module
          + ROOT_DIR + properties.backEnd.rootPackage.replaceAll("\\.", "/") + "/"
          + packageName + CONDITION_PACKAGE
        )

      val repositoryFileName = javaDir + entityName + "DTO.java"

      val params = new java.util.HashMap[String, Object]()

      params.put("name", name)
      params.put("entityName", entityName)
      params.put("rootPackage", properties.backEnd.rootPackage + "." + packageName)

      if (StringUtils.isNotEmpty(repositoryFileName)) {
        val dir = new File(javaDir)
        if (!dir.exists()) dir.mkdirs()
        val template = freemarker.getTemplate(properties.backEnd.dtoTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(repositoryFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }

    }
  }
}
