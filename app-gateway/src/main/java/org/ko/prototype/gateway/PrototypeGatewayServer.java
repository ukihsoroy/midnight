package org.ko.prototype.gateway;

import org.ko.prototype.gateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


//SpringCloud应用启动(Boot, Eureka, Hystrix启动)
@SpringCloudApplication
//开启Zuul API网关服务
@EnableZuulProxy
public class PrototypeGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeGatewayServer.class, args);
    }

    /**
     * 配置Zuul过滤器
     * @return
     */
    @Bean
    public AccessFilter accessFilter () {
        return new AccessFilter();
    }
}
