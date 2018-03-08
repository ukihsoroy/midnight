package org.ko.prototype.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.ko.prototype.admin.bean.domain.AdminUserModel;
import org.ko.prototype.admin.service.AdminService;
import org.ko.prototype.support.helper.Helper;
import org.ko.prototype.support.type.AppCode;
import org.ko.prototype.support.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.ko.prototype.support.constants.ApiConstraintConstants.MaxLength;

@Api(value = "用户入口",description="AdminController")
@RestController
@RequestMapping("admin")
@Validated
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
    private AdminService adminService;

	@GetMapping
	public String get () {
		return "Hello, World!";
	}


	@ApiOperation("登录，访问 /token/image 查看imageToken")
	@PostMapping("login")
	public View<AdminUserModel> login(
		@ApiParam(name = "username", value="用户名", required = true) @RequestParam(value="username") @NotBlank(message="用户名不能为空") @Length(max=MaxLength.Username, message="用户名过长") String username,
		@ApiParam(name = "password", value="密码", required = true) @RequestParam(value="password") @NotBlank(message="密码不能为空") @Length(max=MaxLength.Password,message="密码过长") String password,
		@ApiParam("验证码") @RequestParam(value="imageToken") @NotBlank(message="验证码不能为空") @Length(max=MaxLength.ImageToken, message="验证码过长") String imageToken,
		HttpServletRequest request
	) throws Exception {
		return adminService.login(username, password, imageToken, Helper.getRemoteIPAddress(request));
	}

	@ApiOperation("退出登录")
	@GetMapping("logout")
	public View logout() throws Exception {
		return adminService.logout();
	}

	@ApiOperation("用户未登录状态提示, security跳转")
	@GetMapping("security/timeout")
	public View securityTimeout() throws Exception {
		return new View(AppCode.SESSION_TIME_OUT);
	}

	@ApiOperation("用户未授权状态提示, security跳转")
	@GetMapping("security/auth")
	public View securityAuth() throws Exception {
		return new View(AppCode.NOT_AUTHORIZED);
	}
}
