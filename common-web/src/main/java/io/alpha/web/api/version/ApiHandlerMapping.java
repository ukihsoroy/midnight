package io.alpha.web.api.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ApiHandlerMapping extends RequestMappingHandlerMapping {

    private static final Logger logger = LoggerFactory.getLogger(ApiHandlerMapping.class);

    private int latestVersion = 1;

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        // 判断是否有@ApiVersion注解，构建基于@ApiVersion的RequestCondition
        ApiVersionRequestCondition condition = buildFrom(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));

        //保存最大版本号
        if (condition != null && condition.getVersion() > latestVersion) {
            ApiVersionRequestCondition.setMaxVersion(condition.getVersion());
        }
        return condition;
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        // 判断是否有@ApiVersion注解，构建基于@ApiVersion的RequestCondition
        ApiVersionRequestCondition condition = buildFrom(AnnotationUtils.findAnnotation(method, ApiVersion.class));

        //保存最大版本号
        if (condition != null && condition.getVersion() > latestVersion) {
            ApiVersionRequestCondition.setMaxVersion(condition.getVersion());
        }
        return condition;
    }

    private ApiVersionRequestCondition buildFrom(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionRequestCondition(apiVersion.value());
    }
}
