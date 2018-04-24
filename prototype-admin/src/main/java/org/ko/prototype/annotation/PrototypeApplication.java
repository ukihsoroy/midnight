package org.ko.prototype.annotation;

import org.ko.prototype.constants.SystemConstants;
import org.ko.prototype.properties.PrototypeProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//开启Feign客户端
@EnableFeignClients
//向Eureka注册中心发布服务
@EnableEurekaClient
@SpringCloudApplication
@MapperScan(SystemConstants.MAPPER_PACKAGE)
@EnableConfigurationProperties(PrototypeProperties.class)
public @interface PrototypeApplication {}
