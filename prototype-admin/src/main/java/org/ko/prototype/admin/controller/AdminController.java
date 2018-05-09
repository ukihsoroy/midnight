package org.ko.prototype.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.prototype.core.type.AppCode;
import org.ko.prototype.core.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户入口",description="AdminController")
@RestController
@RequestMapping("admin")
@Validated
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@GetMapping
	public String get () {
		return "Hello, World!";
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
