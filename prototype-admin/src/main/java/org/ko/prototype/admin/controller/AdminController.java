package org.ko.prototype.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.ko.prototype.support.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "AdminController", description = "管理接口")
@RequestMapping("admin")
public class AdminController {

    private static final Logger _LOGGER = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/login")
    public String login (@ApiParam("账户名") @RequestParam("username") String username,
                               @ApiParam("密码") @RequestParam("password") String password) {
        return "SUCCESS";
    }


}
