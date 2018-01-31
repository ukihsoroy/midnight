# Spring Cloud Eureka 服务治理

**1.父pom依赖, 使用的Boot-1.59 Cloud-Edgware**
```
    <!--Spring Boot 父依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
        <relativePath/>
    </parent>
      
      
    <dependencies>
      <!--Spring Boot 测试模块-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
      </dependency>
  
      <!--Spring Boot 项目管理-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
      </dependency>
    </dependencies>
    
    <!--Spring Cloud 引用 各模块的版本等-->
    <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Edgware.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
          </dependency>
        </dependencies>
    </dependencyManagement>
```

**2.配置注册中心：eureka-server**

- pom依赖
```
    <dependencies>
        <!--Eureka Server 注册中心依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
    </dependencies>
```

- application.yml配置

```
    server:
      port: 8081  #注册中心端口
    
    spring:
      application:
        name: eureka-server #服务名称
    
    eureka:
      instance:
        hostname: localhost #实例地址
      client:
        serviceUrl:
          defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/  #访问地址
        register-with-eureka: false #不注册自己
        fetch-registry: false #注册中心职责就是维护实例, 不需要检索服务
```

- EurekaServerApplication：boot启动类

```
    //Spring Boot 应用-启动入口
    @SpringBootApplication
    //自动启动Eureka注册中心
    @EnableEurekaServer
    public class EurekaServerApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(EurekaServerApplication.class, args);
        }
    }
```

**3.注册服务**

- pom依赖-引入服务发布相关依赖

```
    <dependencies>
        <!--Netflix服务发布组件 web服务 ribbon负载均衡等-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>
```

- application.yml配置

```
    # 端口号
    server.port: 8082
    # 定义服务名
    spring.application.name: user
    # 定义注册中心地址
    eureka.client.serviceUrl.defaultZone: http://localhost:8081/eureka/
```

- 创建Controller对应发布接口, 使用DiscoveryClient对象获取服务相关信息

```
    @RestController
    @RequestMapping("services")
    public class ServicesController {
    
        //获取服务相关信息
        @Autowired private DiscoveryClient client;
    
        @GetMapping
        public String getServices() {
            String desc = client.description();
            return desc;
        }
    
    }
```

- ComputeServiceApplication: boot启动类

```
    //Spring Boot 程序
    @SpringBootApplication
    //向Eureka注册中心发布服务
    @EnableEurekaClient
    public class ComputeServiceApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ComputeServiceApplication.class, args);
        }
    }
```

**4.启动**

- 启动EurekaServerApplication注册中心
- 启动ComputeServiceApplication发布服务
- 访问：[http://localhost:8081/](http://localhost:8081/)
![页面显示](./image/ko.jpg)

**5.结束**# ko-prototype
