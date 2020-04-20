package org.ko.analysis.rest.system.controller;

import io.github.sigmaol.web.api.Response;
import io.github.sigmaol.web.api.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@RestController
@Api(tags = "系统操作相关")
@Validated
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 请求的缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    @PostMapping("login")
    @ApiOperation("账号密码登录")
    public void login (
            @ApiParam("用户名") @RequestParam String username,
            @ApiParam("用户口令") @RequestParam String password) {}

    @PostMapping("/authentication/mobile")
    @ApiOperation("手机号码登陆")
    public Response<?> mobileLogin(
            @ApiParam("手机号") @RequestParam String mobile,
            @ApiParam("验证码") @RequestParam String smsCode) {
        return Response.of(true);
    }

    @GetMapping("logout")
    @ApiOperation("登出系统")
    public Response<Long> logout () {
        return new Response<>(1L);
    }

    @PostMapping("register")
    @ApiOperation("注册新用户")
    public Response<Long> register (@ApiParam("用户信息对象") @RequestBody UserDTO userDTO, HttpServletRequest request) {
        System.out.println("注册用户");
        Long userId = systemService.register(userDTO, request);
        return new Response<>(userId);
    }

    @GetMapping("valid/user/{column}")
    @ApiOperation("校验是否重复注册")
    public Response<?> validUserUnique(
            @ApiParam("字段名称") @NotBlank(message = "字段名称不能为空") @PathVariable String column,
            @ApiParam("字段值") @NotBlank(message = "字段值不能为空") @RequestParam String value) {
        systemService.validUserUnique(column, value);
        return Response.ok();
    }

    @GetMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ApiOperation("需要权限验证的引导接口")
    public Response<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 拿到引发跳转的请求
         */
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (Objects.nonNull(savedRequest)) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的URL是: {}", targetUrl);
        }
        return Response.fail(ResponseCode.UN_AUTHORIZED);
    }

    @GetMapping("session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Response<String> sessionInvalid () {
        return Response.ok("session失效");
    }
}
