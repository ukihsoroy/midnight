package org.ko.prototype.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建过滤器, 实现不是GET请求参数必须填写AccessToken
 */
public class AccessFilter extends ZuulFilter {


    private static Logger _LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    private static final String GET = "GET";

    /**
     * 过滤器的类型
     * 决定过滤器在请求那个生命周期执行
     * pre: 请求在路由之前调用
     * routing: 路由请求时调用
     * post: 在routing和error过滤器之后被调用
     * error: 处理请求发生错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否要被执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        _LOGGER.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");

        //不是GET请求必须有accessToken
        if(accessToken == null && !GET.equals(request.getMethod())) {
            _LOGGER.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return "access token is empty";
        }
        _LOGGER.info("access token ok");
        return "access token ok";
    }


}
