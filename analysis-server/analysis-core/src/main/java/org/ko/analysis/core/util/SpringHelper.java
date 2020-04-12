package org.ko.analysis.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * SpringHelper
 *
 */
@Component
public class SpringHelper implements ApplicationContextAware {

    public static ServletContext servletContext;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据Bean名称获取实例
     * 
     * @param name
     *            Bean注册名称
     * 
     * @return bean实例
     * 
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 
     * @param request
     * @param beanName
     * @return
     */
    public static Object getBean(HttpServletRequest request, String beanName) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(request.getSession(true)
                        .getServletContext());
        Object bean = ctx.getBean(beanName);
        return bean;
    }

    /**
     * 
     * @param request
     * @param beanName
     * @return
     */
    public static <T> Object getSpringBean(HttpServletRequest request,
                                           String beanName) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(request.getSession(true)
                        .getServletContext());
        Object bean = ctx.getBean(beanName);
        return bean;
    }

    /**
     * 
     * @param beanName
     * @return
     */
    public static <T> Object getSpringBean(String beanName) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        Object bean = ctx.getBean(beanName);
        return bean;
    }

    /**
     * 
     * @param springXmlPath
     * @param beanName
     * @return
     */
    public static Object getSpringBeanByXml(String springXmlPath,
                                            String beanName) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { springXmlPath });
        BeanFactory factory = context;

        Object bean = factory.getBean(beanName);
        return bean;
    }

    /**
     * 
     * @param xmlPath
     * @param beanName
     * @return
     */
    public static Object getSpringBeanByXml(String[] xmlPath, String beanName) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        BeanFactory factory = context;

        Object bean = factory.getBean(beanName);
        return bean;
    }

    /**
     * 
     * @param springXmlPath
     * @param beanName
     * @return
     */
    public static <T> Object getBeanByXml(String springXmlPath, String beanName) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { springXmlPath });
        BeanFactory factory = context;

        Object bean = factory.getBean(beanName);
        return bean;
    }

    /**
     * getter方法
     * 
     * @return the servletContext
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * setter 方法
     * 
     * @param servletContext
     *            the servletContext to set
     */
    public static void setServletContext(ServletContext servletContext) {
        SpringHelper.servletContext = servletContext;
    }


    /**
     *
     * @param cls
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }
}
