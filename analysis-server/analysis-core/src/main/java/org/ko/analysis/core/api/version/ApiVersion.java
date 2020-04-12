package org.ko.analysis.core.api.version;

import java.lang.annotation.*;

/**
 * <p>api 版本管理注解</p>
 * @author zhiyuan.shen
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * api version code.
     * @return version
     */
    int value() default 1;

}
