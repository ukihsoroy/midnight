package org.ko.analysis.rest.menu.service;


import com.baomidou.mybatisplus.service.IService;
import org.ko.analysis.rest.menu.condition.QueryMenuCondition;
import org.ko.analysis.rest.menu.dto.MenuDTO;
import org.ko.analysis.store.ads.domain.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    /**
     * <p>查询菜单列表</p>
     * @param condition
     * @return
     */
    List<MenuDTO> queryMenuList(QueryMenuCondition condition);

    /**
     * <p>通过主键查询菜单</p>
     * @param id
     * @return
     */
    Menu queryMenuInfo(Long id);


    List<MenuDTO> queryMenuByParentId(Long parentId);

    /**
     * <p>创建新的菜单</p>
     * @param menu
     */
    Long createMenu(Menu menu);

    /**
     * <p>通过ID更新菜单</p>
     * @param id Menu Id
     * @param menu 菜单对象
     * @return
     */
    Menu updateMenu(Long id, Menu menu);

    /**
     * <p>删除菜单</p>
     * @param id Menu主键id
     * @return
     */
    Long deleteMenu(Long id);


    /**
     * 通过权限ID查询菜单列表
     * @param roleCode
     * @return
     */
    List<MenuDTO> queryMenuByRoleCode(String roleCode);
}
