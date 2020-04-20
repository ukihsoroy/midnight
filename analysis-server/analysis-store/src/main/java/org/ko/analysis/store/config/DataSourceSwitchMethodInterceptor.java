package org.ko.analysis.store.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author : K.O
 * @date : 2020/04/13
 */
public class DataSourceSwitchMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke (MethodInvocation invocation) throws Throwable {
        final String packageName = invocation.getThis().getClass().getPackage().getName();
        if (packageName.contains("master")) {
            setDataSourceKey(invocation.getMethod(), GlobalConstant.MASTER_DATA_SOURCE_KEY);
        }
        if (packageName.contains("mpp")) {
            setDataSourceKey(invocation.getMethod(), GlobalConstant.MPP_DATA_SOURCE_KEY);
        }
        return invocation.proceed();
    }


    /**
     * 设置数据源key
     */
    private void setDataSourceKey(final Method method ,final String defaultKey) {
        final DynamicDataSource dynamicDataSource = method.getAnnotation(DynamicDataSource.class);
        if (Objects.isNull(dynamicDataSource)) {
            DynamicMultipleDataSource.setDataSourceKey(defaultKey);
            return;
        }
        DynamicMultipleDataSource.setDataSourceKey(dynamicDataSource.value());
    }


}
