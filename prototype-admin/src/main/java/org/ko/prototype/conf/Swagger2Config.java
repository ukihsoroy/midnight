package org.ko.prototype.conf;

import org.ko.prototype.properties.PrototypeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//开启Swagger2
@EnableSwagger2
//boot 配置类
@Configuration
public class Swagger2Config {


    @Autowired private PrototypeProperties prototypeProperties;

    /**
     * 通过 createRestApi函数来构建一个DocketBean
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                /**
                 * 控制暴露出去的路径下的实例
                 * 如果某个接口不想暴露,可以使用以下注解
                 * @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                 */
                .apis(RequestHandlerSelectors.basePackage("org.ko.prototype.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建API文档详细参数
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                prototypeProperties.getAuthor().getName(),
                prototypeProperties.getAuthor().getUrl(),
                prototypeProperties.getAuthor().getUrl());
        return new ApiInfoBuilder()
                .title("Prototype-Admin")
                .contact(contact)    //作者
                .version("1.0")
                .description("API 描述")
                .build();
    }


}
