package org.ko.analysis.conf;

import org.ko.analysis.conf.api.ApiHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>配置</p>
 */
@Configuration
@EnableSwagger2
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
            ContentNegotiationManager mvcContentNegotiationManager,
            FormattingConversionService mvcConversionService,
            ResourceUrlProvider mvcResourceUrlProvider) {

        //Api版本管理
        RequestMappingHandlerMapping handlerMapping = new ApiHandlerMapping();

        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
        return handlerMapping;
    }

    /**
     * <p>异步请求一些配置</p>
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
//        configurer.registerDeferredResultInterceptors()
//        configurer.registerCallableInterceptors()
        //异步调用超时时间
        configurer.setDefaultTimeout(1000);
        //配置线程池
//        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ko"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("QUICK-SIGMA-SERVER")
                .description("快速开放框架swagger2 API文档")
                .version("1.0.0")
                .build();
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
