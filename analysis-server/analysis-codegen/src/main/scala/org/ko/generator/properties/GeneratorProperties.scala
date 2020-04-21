package org.ko.generator.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "sigma.generator")
class GeneratorProperties {

  var prefix = "t_"

  //实体类生成配置
  var entity = new EntityProperties()

  //controller生成配置
  var backEnd = new BackEndProperties()

}
