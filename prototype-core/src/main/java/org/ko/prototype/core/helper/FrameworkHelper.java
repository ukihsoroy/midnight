package org.ko.prototype.core.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FrameworkHelper implements ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(FrameworkHelper.class);

    static ApplicationContext ctx;

    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getResponse();
    }

    public static<T> T getBean(Class<T> type){
        T bean = null;

        try{
            bean = ctx.getBean(type);
        }catch(Exception e){
            log.warn("get bean " + type.getCanonicalName() + " failed", e.getMessage());
        }

        return bean;
    }

    public static<T> T getBean(String name, Class<T> type){
        T bean = null;

        try{
            bean = ctx.getBean(name, type);
        }catch(Exception e){
            log.warn("get bean " + type.getCanonicalName() + " failed", e.getMessage());
        }

        return bean;
    }

    public static String getAppNamePath(){
        return getHttpServletRequest().getContextPath();
    }

    public static String getAppServerRoot(){
        HttpServletRequest request = getHttpServletRequest();
        return StringUtils.join(new Object[] { request.getScheme(),
                "://", request.getServerName(), ":", request.getServerPort(),
                request.getContextPath(), "/" });
    }

    public static String getAppRootLocalPath(){
        return getHttpServletRequest().getServletContext().getRealPath("/");
    }

    public static String getOsName(){
        return System.getProperty("os.name");
    }

    public static String getPathSeparator(){
        return System.getProperty("path.separator");
    }



    public static List<String> getHostIps() throws UnknownHostException {
        List<String> ips = new ArrayList<>();

        InetAddress[] ias = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());

        if(ArrayUtils.isNotEmpty(ias)){
            for(InetAddress ia : ias){
                ips.add(ia.getHostAddress());
            }
        }
        return ips;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
