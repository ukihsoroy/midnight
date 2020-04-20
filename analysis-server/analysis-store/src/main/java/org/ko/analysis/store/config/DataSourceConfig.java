package org.ko.analysis.store.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : K.O
 * @date : 2020/04/13
 */
@Configuration
public class DataSourceConfig {

    /**
     * 动态数据源配置
     *
     * @param masterDataSource : 应用数据源
     * @param mppDataSource  : 操作数据源
     * @return
     */
    @Bean
    public DynamicMultipleDataSource multipleDataSource(@Qualifier(GlobalConstant.MASTER_DATA_SOURCE_KEY) DataSource masterDataSource,
                                                        @Qualifier(GlobalConstant.MPP_DATA_SOURCE_KEY) DataSource mppDataSource) {
        DynamicMultipleDataSource dynamicMultipleDataSource = new DynamicMultipleDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(GlobalConstant.MASTER_DATA_SOURCE_KEY, masterDataSource);
        targetDataSources.put(GlobalConstant.MPP_DATA_SOURCE_KEY, mppDataSource);
        dynamicMultipleDataSource.setTargetDataSources(targetDataSources);
        dynamicMultipleDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicMultipleDataSource;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.master" )
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.mpp" )
    public DataSource mppDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicMultipleDataSource dynamicMultipleDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicMultipleDataSource );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DynamicMultipleDataSource dynamicMultipleDataSource) {
        return new DataSourceTransactionManager( dynamicMultipleDataSource );
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DynamicMultipleDataSource dynamicMultipleDataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicMultipleDataSource);
        return sqlSessionFactoryBean;
    }


}



