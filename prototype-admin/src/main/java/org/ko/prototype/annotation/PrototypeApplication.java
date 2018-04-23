package org.ko.prototype.annotation;

import org.ko.prototype.properties.PrototypeProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//开启Feign客户端
@EnableFeignClients
//向Eureka注册中心发布服务
@EnableEurekaClient
@SpringCloudApplication
@MapperScan("org.ko.prototype.data")
@EnableConfigurationProperties(PrototypeProperties.class)
public @interface PrototypeApplication {}
