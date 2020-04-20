package org.ko.analysis.rest.role.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.ko.analysis.conf.api.ResponseCode;
import org.ko.analysis.conf.exp.BusinessException;
import org.ko.analysis.rest.role.condition.QueryRoleCondition;
import org.ko.analysis.rest.role.repository.RoleMenuRepository;
import org.ko.analysis.rest.role.repository.RoleRepository;
import org.ko.analysis.rest.role.service.RoleService;
import org.ko.analysis.store.ads.domain.Role;
import org.ko.analysis.store.ads.domain.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl extends ServiceImpl<RoleRepository, Role> implements RoleService {

    /**
     * 权限数据库对象
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 权限菜单关联数据
     */
    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Override
    public List<Role> queryRoleList(QueryRoleCondition condition) {
        return roleRepository.selectList(Condition.EMPTY);
    }

    @Override
    public Role queryRoleInfo(String code) {
        return roleRepository.selectOne(Role.builder().code(code).build());
    }

    @Override
    public Long createRole(Role role) {
        if (roleRepository.insert(role) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return role.getId();
    }

    @Override
    public Role updateRole(String code, Role role) {
        role.setCode(code);
        if (roleRepository.update(role, Condition.create().eq("code", code)) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return role;
    }

    @Override
    public String deleteRole(String code) {
        roleMenuRepository.delete(Condition.create().eq("code", code));
        if (roleRepository.delete(Condition.create().eq("code", code)) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return code;
    }


}
