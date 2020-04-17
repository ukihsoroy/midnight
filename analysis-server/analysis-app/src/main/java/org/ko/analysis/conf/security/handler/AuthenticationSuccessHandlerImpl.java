package org.ko.analysis.conf.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ko.analysis.conf.api.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登陆成功的逻辑
 * SavedRequestAwareAuthenticationSuccessHandler spring默认的成功处理器
 * AuthenticationSuccessHandler 成功处理器接口
 */
@Component
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param request
     * @param response
     * @param authentication 核心接口, 封装认证信息(发起认证请求信息, ip, session, 认证通过的UserDetailsService的用户信息)
     *                       #{@link Authentication}
     *
     *                       {"authorities":
     *                       [{"authority":"admin"}],
     *                       "details":{
     *                       "remoteAddress":"0:0:0:0:0:0:0:1","sessionId":null},
     *                       "authenticated":true,
     *                       "principal":
     *                       {"password":null,"username":"user",
     *                       "authorities":[{"authority":"admin"}],
     *                       "accountNonExpired":true,"accountNonLocked":true,
     *                       "credentialsNonExpired":true,"enabled":true},"credentials":null,"name":"user"}
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        logger.info("登陆成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Response.ok(authentication)));
    }
}
