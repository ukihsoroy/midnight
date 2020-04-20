package org.ko.analysis.rest.menu.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ko.analysis.rest.menu.condition.QueryMenuCondition;
import org.ko.analysis.rest.menu.dto.MenuDTO;
import org.ko.analysis.store.ads.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseMapper<Menu> {

    List<MenuDTO> queryMenuList(QueryMenuCondition condition);

    List<MenuDTO> queryMenuByParentId(Long parentId);

    List<MenuDTO> queryMenuByRoleCode(String roleCode);
}
