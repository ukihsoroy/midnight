package org.ko.analysis.rest.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.analysis.rest.role.repository.RoleMenuRepository;
import org.ko.analysis.rest.role.service.RoleMenuService;
import org.ko.analysis.store.master.constants.RoleMenuConstants;
import org.ko.analysis.store.master.domain.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuRepository, RoleMenu> implements RoleMenuService {

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Override
    public Long createRoleMenu(String roleCode, List<Long> menuIds) {
        List<RoleMenu> roleMenus =  menuIds.stream().map(menuId -> buildRoleMenu(roleCode, menuId)).collect(Collectors.toList());
        return roleMenuRepository.insertList(roleMenus);
    }

    @Override
    public Integer removeRoleMenu(String roleCode) {
        return roleMenuRepository.delete(new QueryWrapper<RoleMenu>().eq(RoleMenuConstants.Columns.ROLE_CODE, roleCode));
    }

    private RoleMenu buildRoleMenu (String roleCode, Long menuId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleCode(roleCode);
        roleMenu.setMenuId(menuId);
        return roleMenu;
    }
}
