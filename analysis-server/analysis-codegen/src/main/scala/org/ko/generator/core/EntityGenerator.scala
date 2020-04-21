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
  * @param freemarker
  * @param properties
  */
@Component
class EntityGenerator @Autowired()(
                                    freemarker: Configuration,
                                    properties: GeneratorProperties
                                  ) extends AbstractGenerator {

  val ENTITY_PACKAGE = "/entity/"
  val CONSTANTS_PACKAGE = "/constants/"

  override def executor(names: List[String]): Unit = {
    names.foreach { name =>
      //全部字段
      val columns = findColumnByTableName(name)
      //表名字
      val entityName = reformatTable(name, properties.prefix)
      //表注释
      val comment = findTableComment(name)

      val dir = new File(this.getClass.getClassLoader.getResource(".").toURI).getAbsolutePath
      val index = dir.indexOf("target")
      val moduleRoot = new File(dir.substring(0, index)).getParent

      val javaDir = moduleRoot + "/" + properties.entity.module + ROOT_DIR + properties.entity.rootPackage.replaceAll("\\.", "/")
      val entityFileName = javaDir + ENTITY_PACKAGE + entityName + ".java"

      val params = new java.util.HashMap[String, Object]()

      params.put("name", name)
      params.put("entityName", entityName)
      params.put("comment", comment)
      params.put("columns", columns.asJavaCollection)
      params.put("rootPackage", properties.entity.rootPackage)

      if (StringUtils.isNotEmpty(entityFileName)) {
        val template = freemarker.getTemplate(properties.entity.entityTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(entityFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }

      val constantsFileName = javaDir + CONSTANTS_PACKAGE + entityName + "Constants.java"

      if (StringUtils.isNotEmpty(constantsFileName)) {
        val template = freemarker.getTemplate(properties.entity.constantsTemplate)
        val out = new OutputStreamWriter(new FileOutputStream(new File(constantsFileName)), "UTF-8")
        template.process(params, out)
        out.close()
      }
    }
  }
}
