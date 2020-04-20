package org.ko.analysis.rest.user.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ko.analysis.rest.role.dto.RoleDTO;
import org.ko.analysis.store.master.domain.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends BaseMapper<UserRole> {

    List<RoleDTO> findRolesByUserId(@Param("id") Long id);

    Long insertList(@Param("userRoles") List<UserRole> userRoles);

}
