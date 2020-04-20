package org.ko.analysis.store.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Objects;

public class DataSourceSwitch {

    @Before("execution(* org.ko.**.master.*.*Service.*(..))")
    public void dataPlatform (JoinPoint joinPoint){
        setDataSourceKey(joinPoint, GlobalConstant.MASTER_DATA_SOURCE_KEY);
    }

    @Before("execution(* org.ko.**.mpp.*.*Service.*(..))")
    public void shop(JoinPoint joinPoint) {
        setDataSourceKey(joinPoint, GlobalConstant.MPP_DATA_SOURCE_KEY);
    }

    /**
     * 设置数据源key
     */
    private void setDataSourceKey(JoinPoint joinPoint ,final String defaultKey) {
        final Method method = this.currentMethod(joinPoint);
        final DynamicDataSource dynamicDataSource = method.getAnnotation(DynamicDataSource.class);
        if (Objects.isNull(dynamicDataSource)) {
            DynamicMultipleDataSource.setDataSourceKey(defaultKey);
            return;
        }
        DynamicMultipleDataSource.setDataSourceKey(dynamicDataSource.value());
    }

    /**
     * 获取当前执行的方法
     */
    private Method currentMethod(JoinPoint joinPoint){
        return ((MethodSignature)joinPoint.getSignature()).getMethod();
    }
}
