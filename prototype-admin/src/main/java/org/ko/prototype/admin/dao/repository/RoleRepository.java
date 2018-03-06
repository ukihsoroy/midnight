package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.Role;
import org.ko.prototype.data.master.domain.bean.RoleExample;
import org.ko.prototype.data.master.domain.constants.RoleConstants;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:20
 * 
 */
@Repository
public class RoleRepository extends GenericRepository<Role, RoleExample> {

	private static final Short AVAILABLE_VALUE = RoleConstants.Values.DeleteStatus.Available;
	
	@Autowired private RoleMapper mapper;

	public List<Role> findAllAvailable(){
		RoleExample e = new RoleExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public Role findAvailableById(Integer id){
		Role record = super.findById(id);
		return Short.valueOf(RoleConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		RoleExample e = new RoleExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		Role record = new Role();
		record.setId(id);
		record.setDeleteStatus(RoleConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
