package org.ko.prototype.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ko.prototype.core.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    private static final Logger _LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

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
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        _LOGGER.info("登陆成功");
        View<Object> view = new View<>();
        view.setModel(authentication.getPrincipal());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(view));
    }
}
