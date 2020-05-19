package org.ko.analysis.rest.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.analysis.conf.api.Response;
import org.ko.analysis.help.SessionHolder;
import org.ko.analysis.rest.user.condition.QueryUserCondition;
import org.ko.analysis.rest.user.dto.UserDTO;
import org.ko.analysis.rest.user.service.UserService;
import org.ko.analysis.store.master.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("user")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("查询用户列表")
    public Response<IPage<UserDTO>> queryUserList(@ApiParam("列表查询参数") @ModelAttribute QueryUserCondition<User> condition) {
        //1. 查询用户列表数据
        IPage<UserDTO> page = userService.queryUserList(condition);

        //2. 如果不为空
        return Response.ok(page);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID获取用户详细")
    public Response<UserDTO> queryUserInfo (
            @ApiParam("用户ID") @PathVariable Long id) {
        logger.info("UserController#queryUserInfo");
        User user = userService.queryUserInfo(id);
        return Response.ok(this.map(user));
    }

    @GetMapping("info")
    @ApiOperation("获取当前登录用户信息")
    public Response<UserDTO> loginUser () {
        return Response.ok(SessionHolder.loginUser());
    }

    @PostMapping
    @ApiOperation("新建用户")
    public Response<Long> createUser (
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        Long userId = userService.createUser(map(userDTO));
        return Response.ok(userId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("通过ID更新用户信息")
    public Response<User> updateUser (
            @ApiParam("用户ID主键") @PathVariable Long id,
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, map(userDTO));
        return Response.ok(user);
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("通过ID删除用户")
    public Response<Long> removeUser (
            @ApiParam("用户ID") @PathVariable Long id) {
        Long result = userService.removeUser(id);
        return Response.ok(result);
    }

    /**
     * User mapTo UserDTO
     * @param User
     * @return
     */
    private UserDTO map (User User) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(User, userDTO);
        return userDTO;
    }

    /**
     * UserDTO mapTo User
     * @param userDTO
     * @return
     */
    private User map (UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
