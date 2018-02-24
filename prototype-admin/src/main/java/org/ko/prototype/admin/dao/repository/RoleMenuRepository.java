package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.bean.domain.RoleMenu;
import org.ko.prototype.data.bean.domain.RoleMenuExample;
import org.ko.prototype.data.constants.domain.RoleMenuConstants;
import org.ko.prototype.data.dao.mapper.RoleMenuMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:20
 * 
 */
@Repository
public class RoleMenuRepository extends GenericRepository<RoleMenu, RoleMenuExample> {

	@Autowired private RoleMenuMapper mapper;

}
