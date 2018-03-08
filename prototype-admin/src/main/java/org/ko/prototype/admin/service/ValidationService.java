package org.ko.prototype.admin.service;

import org.ko.prototype.admin.bean.domain.AdminUserModel;
import org.ko.prototype.admin.dao.repository.AdminUserRepository;
import org.ko.prototype.data.master.domain.bean.AdminUserExample;
import org.ko.prototype.data.master.domain.constants.AdminUserConstants;
import org.ko.prototype.support.exception.AppException;
import org.ko.prototype.support.helper.Helper;
import org.ko.prototype.support.type.AppCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ValidationService {

	private static final Logger log = LoggerFactory.getLogger(ValidationService.class);
	
	@Autowired
    protected AdminUserRepository adminUserRepository;


	/**
	 * 确保用户存在
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUserModel assureAdminUserExists(String username, String password) throws Exception {
		String pwd = Helper.encryptUserPassword(username, password);
		AdminUserExample e = new AdminUserExample();
		e.createCriteria()
				.andDeleteStatusEqualTo(AdminUserConstants.Values.DeleteStatus.Available)
				.andLoginNameEqualTo(username)
				.andPasswordEqualTo(pwd);
		AdminUserModel user = adminUserRepository.findOne(e, AdminUserModel.class);

		if(user == null){
			throw new AppException(AppCode.INVALID_USER_OR_PASSWORD);
		}

		return user;
	}

}
