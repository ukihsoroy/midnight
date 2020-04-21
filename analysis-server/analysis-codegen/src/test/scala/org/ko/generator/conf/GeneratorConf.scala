package org.ko.generator.conf

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource

object GeneratorConf {

  def dataSource(): MysqlDataSource ={
    val dataSource = new MysqlDataSource()
    dataSource.setDatabaseName("sigma_server")
    dataSource.setPort(3306)
    dataSource.setUser("root")
    dataSource.setPassword("tiger")
    dataSource
  }

}
