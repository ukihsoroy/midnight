package org.ko.prototype.core.conf.ds;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public abstract class DruidAbstractDataSourceConfig implements IDataSourceConfig{


    @Override
    public DataSource buildDataSource(String driverClass,
                                      String url,
                                      String username,
                                      String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
