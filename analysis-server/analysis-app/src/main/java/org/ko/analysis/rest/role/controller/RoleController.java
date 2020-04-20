package org.ko.analysis.rest.role.controller;


import io.github.sigmaol.web.api.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.data.entity.Role;
import org.ko.sigma.rest.menu.dto.MenuDTO;
import org.ko.sigma.rest.menu.service.MenuService;
import org.ko.sigma.rest.role.condition.QueryRoleCondition;
import org.ko.sigma.rest.role.dto.RoleDTO;
import org.ko.sigma.rest.role.service.RoleMenuService;
import org.ko.sigma.rest.role.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "权限接口")
@RestController
@RequestMapping("role")
@Validated
public class RoleController {

    /**
     * 权限service
     */
    @Autowired
    private RoleService roleService;

    /**
     * 菜单service
     */
    @Autowired
    private MenuService menuService;

    /**
     * 权限菜单service
     */
    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping
    @ApiOperation("查询全部权限")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<List<RoleDTO>> queryRoleList(@ApiParam("权限查询参数") @ModelAttribute QueryRoleCondition condition) {
        //1. 查询用户列表数据
        List<Role> roles = roleService.queryRoleList(condition);

        List<RoleDTO> roleDTOS = roles.stream().map(this::map).collect(Collectors.toList());
        return new Response<>(roleDTOS);
    }

    @GetMapping("{code}")
    @ApiOperation("通过ID查询权限")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<RoleDTO> queryRoleInfo (@ApiParam("主键") @PathVariable String code) {
        Role role = roleService.queryRoleInfo(code);
        return new Response<>(map(role));
    }

    @PostMapping
    @ApiOperation("新增权限")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<Long> createRole (
            @ApiParam("权限传输对象实体") @RequestBody RoleDTO roleDTO) {
        Long roleId = roleService.createRole(map(roleDTO));;
        return new Response<>(roleId);
    }

    @PutMapping("{code}")
    @ApiOperation("修改权限")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<RoleDTO> updateRole (
            @ApiParam("用户ID主键") @PathVariable String code,
            @ApiParam("用户传输对象实体") @RequestBody RoleDTO roleDTO) {
        Role role = roleService.updateRole(code, map(roleDTO));
        return new Response<>(map(role));
    }

    @DeleteMapping("{code}")
    @ApiOperation("删除权限")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<String> deleteRole(@ApiParam("权限编码") @PathVariable String code) {
        String result = roleService.deleteRole(code);
        return new Response<>(result);
    }

    @GetMapping("{roleCode}/menu")
    @ApiOperation("查询当前权限下菜单")
    public Response<List<MenuDTO>> queryMenuByRoleCode (@ApiParam("权限ID") @PathVariable String roleCode) {
        List<MenuDTO> menuDTOS = menuService.queryMenuByRoleCode(roleCode);
        return new Response<>(menuDTOS);
    }

    /**
     * 该方法会先删除全部菜单，在新增新的数据
     * @param roleCode 权限代码
     * @param menuIds 菜单编号
     * @return 插入条数
     */
    @PostMapping("{roleCode}/menu")
    @ApiOperation("为当前权限添加菜单")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<Long> createRoleMenu (
            @ApiParam("权限ID") @PathVariable String roleCode,
            @ApiParam("菜单ID列表") @RequestBody List<Long> menuIds) {

        // 删除原有菜单
        roleMenuService.removeRoleMenu(roleCode);

        // 新增菜单
        Long count = roleMenuService.createRoleMenu(roleCode, menuIds);
        return new Response<>(count);
    }

    /**
     * Role mapTo RoleDTO
     * @param role
     * @return roleDTO
     */
    private RoleDTO map (Role role) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        return roleDTO;
    }

    /**
     * RoleDTO mapTo Role
     * @param roleDTO
     * @return
     */
    private Role map (RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

}
