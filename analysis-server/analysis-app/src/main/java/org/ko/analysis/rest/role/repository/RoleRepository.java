package org.ko.analysis.rest.role.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ko.analysis.store.ads.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseMapper<Role> {

}
