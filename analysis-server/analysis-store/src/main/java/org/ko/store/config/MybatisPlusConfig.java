package org.ko.store.config;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : K.O
 * @date : 2020/04/13
 */
@Configuration
@MapperScan({
        "org.ko.store.ads.repository",
        "org.ko.store.ods.repository"
})
public class MybatisPlusConfig {


    /**
     * mybatis-plus 性能分析拦截器<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor () {
        return new PerformanceInterceptor();
    }


}
