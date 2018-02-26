package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.data.master.dao.mapper.AdminUserMapper;
import org.ko.prototype.data.master.dao.repository.GenericRepository;
import org.ko.prototype.data.master.domain.bean.AdminUser;
import org.ko.prototype.data.master.domain.bean.AdminUserExample;
import org.ko.prototype.data.master.domain.constants.AdminUserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:13
 * 
 */
@Repository
public class AdminUserRepository extends GenericRepository<AdminUser, AdminUserExample> {

	private static final Short AVAILABLE_VALUE = AdminUserConstants.Values.DeleteStatus.Available;
	
	@Autowired private AdminUserMapper mapper;

	public List<AdminUser> findAllAvailable(){
		AdminUserExample e = new AdminUserExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public AdminUser findAvailableById(Integer id){
		AdminUser record = super.findById(id);
		return Short.valueOf(AdminUserConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		AdminUserExample e = new AdminUserExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		AdminUser record = new AdminUser();
		record.setId(id);
		record.setDeleteStatus(AdminUserConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
