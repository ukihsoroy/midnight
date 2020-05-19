package org.ko.analysis.rest.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.analysis.store.master.domain.RoleMenu;

import java.util.List;

/**
 * @author zhiyuan.shen
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * <p>创建权限和菜单关联</p>
     * @param roleCode 权限编码
     * @param menuIds 菜单ID
     * @return 权限ID
     */
    Long createRoleMenu(String roleCode, List<Long> menuIds);

    /**
     * 删除权限代码下的菜单
     * @param roleCode 权限代码
     * @return
     */
    Integer removeRoleMenu(String roleCode);
}
