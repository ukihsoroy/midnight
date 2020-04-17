package org.ko.analysis.conf.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ko.analysis.conf.api.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        logger.info("推出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Response.ok("退出成功")));
    }
}
