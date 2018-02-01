package org.ko.prototype.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


//开启Feign客户端
//@EnableFeignClients
//向Eureka注册中心发布服务
//@EnableEurekaClient
//@SpringCloudApplication
@SpringBootApplication
public class PrototypeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeAdminApplication.class, args);
    }
}
