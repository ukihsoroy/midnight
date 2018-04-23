package org.ko.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//开启Feign客户端
@EnableFeignClients
//向Eureka注册中心发布服务
@EnableEurekaClient
@SpringCloudApplication
public class PrototypeAdminServer {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeAdminServer.class, args);
    }
}
