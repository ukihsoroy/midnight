package org.ko.analysis.conf.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = AdsDataSourceConf.PACKAGE, sqlSessionFactoryRef = "adsSqlSessionFactory")
public class AdsDataSourceConf {

    // 精确到 mapper.cluster 目录，以便跟其他数据源隔离
    public static final String PACKAGE = "org.ko.analysis.ads.repository";
    public static final String MAPPER_LOCATION = "classpath:org/ko/analysis/ads/repository/*.xml";

    @Value("${ads.datasource.url}")
    private String url;

    @Value("${ads.datasource.username}")
    private String user;

    @Value("${ads.datasource.password}")
    private String password;

    @Value("${ads.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "adsDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "adsTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "adsSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("adsDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(AdsDataSourceConf.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}