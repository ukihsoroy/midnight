package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.User;
import org.ko.prototype.data.master.domain.bean.UserExample;
import org.ko.prototype.data.master.domain.constants.UserConstants;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:23
 * 
 */
@Repository
public class UserRepository extends GenericRepository<User, UserExample> {

	private static final Short AVAILABLE_VALUE = UserConstants.Values.DeleteStatus.Available;
	
	@Autowired private UserMapper mapper;

	public List<User> findAllAvailable(){
		UserExample e = new UserExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public User findAvailableById(Integer id){
		User record = super.findById(id);
		return Short.valueOf(UserConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		UserExample e = new UserExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		User record = new User();
		record.setId(id);
		record.setDeleteStatus(UserConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
