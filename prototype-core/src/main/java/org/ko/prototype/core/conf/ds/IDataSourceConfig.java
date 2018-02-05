package org.ko.prototype.core.conf.ds;

import javax.sql.DataSource;

public interface IDataSourceConfig {

    /**
     * 配置数据源
     * @return
     */
    DataSource dataSource();

    /**
     * 构建数据源
     * @param driverClass 驱动
     * @param url 连接
     * @param username 用户名
     * @param password 密码
     * @return
     */
    DataSource buildDataSource(String driverClass, String url, String username, String password);

}
