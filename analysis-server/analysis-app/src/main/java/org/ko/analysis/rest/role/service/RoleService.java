package org.ko.analysis.rest.role.service;


import com.baomidou.mybatisplus.service.IService;
import org.ko.analysis.rest.role.condition.QueryRoleCondition;
import org.ko.analysis.store.master.domain.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * <p>查询权限列表</p>
     * @param condition
     * @return
     */
    List<Role> queryRoleList(QueryRoleCondition condition);

    /**
     * <p>通过主键查询权限</p>
     * @param code
     * @return
     */
    Role queryRoleInfo(String code);

    /**
     * <p>创建新的权限</p>
     * @param role
     */
    Long createRole(Role role);

    /**
     * <p>通过ID</p>
     * @param code role code
     * @param role 权限对象
     * @return
     */
    Role updateRole(String code, Role role);

    /**
     * <p>删除权限</p>
     * @param code role编码
     * @return
     */
    String deleteRole(String code);

}
