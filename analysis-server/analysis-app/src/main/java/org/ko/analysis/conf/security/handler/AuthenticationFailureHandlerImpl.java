package org.ko.analysis.conf.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ko.analysis.conf.api.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登陆失败的回调
 * AuthenticationFailureHandler 失败处理器接口
 * SimpleUrlAuthenticationFailureHandler spring 默认的失败处理
 */
@Component
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        logger.info("登陆失败;");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Response.ok(exception.getMessage())));
    }
}
