package org.ko.analysis.store.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author : K.O
 * @date : 2020/04/13
 */
@Configuration
@MapperScan({
        "org.ko.analysis.**.repository"
})
public class MybatisPlusConfig extends MetaObjectHandler {

    /**
     * mybatis-plus 性能分析拦截器<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor () {
        return new PerformanceInterceptor();
    }


    private static final String CREATE_USER = "createUser";
    private static final String GMT_CREATE = "gmtCreate";
    private static final String MODIFIED_USER = "modifiedUser";
    private static final String GMT_MODIFIED = "gmtModified";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(CREATE_USER, "admin", metaObject);
        this.setFieldValByName(GMT_CREATE, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(MODIFIED_USER, "admin", metaObject);
        this.setFieldValByName(GMT_MODIFIED, new Date(), metaObject);
    }
}
