package org.ko.analysis.rest.menu.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.analysis.conf.api.Response;
import org.ko.analysis.rest.menu.condition.QueryMenuCondition;
import org.ko.analysis.rest.menu.dto.MenuDTO;
import org.ko.analysis.rest.menu.service.MenuService;
import org.ko.analysis.store.ads.domain.Menu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
@Validated
@Api(tags = "菜单模块")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    @ApiOperation("查询全部菜单")
    public Response<List<MenuDTO>> queryMenuList (@ApiParam("查询参数") @ModelAttribute QueryMenuCondition condition) {
        // 1.查询全部菜单
        List<MenuDTO> menus = menuService.queryMenuList(condition);
        return Response.ok(menus);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID查询菜单详情")
    public Response<MenuDTO> queryMenuInfo (@ApiParam("菜单ID") @PathVariable Long id) {
        Menu menu = menuService.queryMenuInfo(id);
        return new Response<>(map(menu));
    }

    @GetMapping("child/{parentId:\\d+}")
    @ApiOperation("通过父ID查询子菜单列表")
    public Response<List<MenuDTO>> queryMenuByParentId (@ApiParam("父级菜单ID") @PathVariable Long parentId) {
        List<MenuDTO> menuDTOS = menuService.queryMenuByParentId(parentId);
        return new Response<>(menuDTOS);
    }

    @PostMapping
    @ApiOperation("新增菜单")
    public Response<Long> createMenu (
            @ApiParam("菜单传输对象实体") @RequestBody MenuDTO menuDTO) {
        Long roleId = menuService.createMenu(map(menuDTO));
        return new Response<>(roleId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("通过ID修改菜单")
    public Response<MenuDTO> updateMenu (
            @ApiParam("菜单主键ID") @PathVariable Long id,
            @ApiParam("菜单DTO对象实体") @RequestBody MenuDTO menuDTO) {
        Menu result = menuService.updateMenu(id, map(menuDTO));
        return new Response<>(map(result));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("通过ID删除菜单")
    public Response<Long> removeMenu (@ApiParam("被删除菜单ID") @PathVariable Long id) {
        Long result = menuService.deleteMenu(id);
        return new Response<>(result);
    }

    /**
     * menu to menuDTO
     * @param menu
     * @return
     */
    private MenuDTO map (Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        if (menu != null) {
            BeanUtils.copyProperties(menu, menuDTO);
        }
        return menuDTO;
    }

    /**
     * menuDTO to menu
     * @param menuDTO
     * @return
     */
    private Menu map (MenuDTO menuDTO) {
        Menu menu = new Menu();
        if (menuDTO != null) {
            BeanUtils.copyProperties(menuDTO, menu);
        }
        return menu;
    }
}
