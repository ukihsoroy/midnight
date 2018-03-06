package org.ko.prototype.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.ko.prototype.admin.bean.domain.AdminUserModel;
import org.ko.prototype.admin.service.AdminService;
import org.ko.prototype.data.master.domain.bean.User;
import org.ko.prototype.support.bean.model.AuthenticationBean;
import org.ko.prototype.support.constants.ApiConstraintConstants;
import org.ko.prototype.support.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.ko.prototype.support.constants.ApiConstraintConstants.*;

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

//	@GetMapping("login")
//	public View<User> login (AuthenticationBean authenticationBean) {
//		return new View();
//	}


	
	@ApiOperation("登录，访问 /token/image 查看imageToken")
	@PostMapping("login")
	public View<AdminUserModel> login(
		@ApiParam(name = "username", value="用户名", required = true) @RequestParam(value="username") @NotBlank(message="用户名不能为空") @Length(max=MaxLength.Username, message="用户名过长") String username,
		@ApiParam(name = "password", value="密码", required = true) @RequestParam(value="password") @NotBlank(message="密码不能为空") @Length(max=MaxLength.Password,message="密码过长") String password,
		@ApiParam("验证码") @RequestParam(value="imageToken") @NotBlank(message="验证码不能为空") @Length(max=MaxLength.ImageToken, message="验证码过长") String imageToken,
		HttpServletRequest request
	) throws Exception {
		return adminService.login(username, password, imageToken, Utils.getRemoteIpAddress(request));
	}

	@ApiResponseObject
	@ApiMethod(summary="注销",description="注销")
	@RequestMapping(value="/logout",method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public View logout() throws Exception {
		return adminService.logout();
	}

	@ApiResponseObject
	@ApiMethod(summary="验证用户",description="验证用户")
	@RequestMapping(value="/validate",method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public BeanView<UserProfile> validate(@ApiQueryParam(description="重新读取用户信息",format="0-不重新读取;1-重新读取") @RequestParam(value="reload",required=false,defaultValue="0") String reload) throws Exception {
		UserProfile profile = null;
		if(NumberUtils.convertToInt(reload) == 0){
			profile = AppContext.getSession().get(AdminConstants.Session.UserProfile);
		}else{
			profile = adminService.reload();
		}

		BeanView<UserProfile> view = new BeanView<>();
		view.setModel(profile);

		return view;
	}

	@ApiResponseObject
	@ApiMethod(summary="用户未登录状态提示，shiro跳转",description="用户未登录状态提示，shiro跳转")
	@RequestMapping(value="/nologin",method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public View nologin() throws Exception {
		return new View(ApiCode.SessionTimeout.getCode(), ApiCode.SessionTimeout.getMessage());
	}

	@ApiResponseObject
	@ApiMethod(summary="用户未授权状态提示，shiro跳转",description="用户未授权状态提示，shiro跳转")
	@RequestMapping(value="/noauth",method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public View noauth() throws Exception {
		return new View(ApiCode.NotAuthorized.getCode(), ApiCode.NotAuthorized.getMessage());
	}
}
