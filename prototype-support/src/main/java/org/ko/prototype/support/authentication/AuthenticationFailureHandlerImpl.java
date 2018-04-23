package org.ko.prototype.support.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.ko.prototype.support.type.AppCode;
import org.ko.prototype.support.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *
 */
@Component
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger _LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        _LOGGER.info("AuthenticationFailureHandler#onAuthenticationFailure error: {}", exception.getMessage());

        //返回视图
        View<String> view = new View<>(AppCode.ERROR);
        view.setMassage(exception.getMessage());

        //返回前台
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(view));
    }
}
