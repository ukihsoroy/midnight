package org.ko.prototype.data.master;

import org.apache.ibatis.session.SqlSessionFactory;
import org.ko.prototype.core.conf.ds.DruidAbstractDataSourceConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MasterDatasourceConfig.MAPPER_SCAN, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDatasourceConfig extends DruidAbstractDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String MAPPER_SCAN = "org.ko.prototype.data.master.dao";
//    static final String MAPPER_LOCATION = "classpath:mappers/master/*.xml";

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource dataSource() {
        return this.buildDataSource(driverClass, url, user, password);
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MasterDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
