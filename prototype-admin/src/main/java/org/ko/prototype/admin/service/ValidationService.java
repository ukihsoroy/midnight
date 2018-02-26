package org.ko.prototype.admin.service;

import org.ko.prototype.admin.bean.domain.AdminUserModel;
import org.ko.prototype.admin.dao.repository.AdminUserRepository;
import org.ko.prototype.data.master.domain.bean.AdminUserExample;
import org.ko.prototype.data.master.domain.constants.AdminUserConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
	 * @throws Exception
	 */
	public AdminUserModel assureAdminUserExists(String username, /*@LogIgnore*/ String password) throws Exception {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();

		String pwd = encoder.encodePassword(password, username);
		AdminUserExample e = new AdminUserExample();
		e.createCriteria()
			.andDeleteStatusEqualTo(AdminUserConstants.Values.DeleteStatus.Available)
			.andLoginNameEqualTo(username)
			.andPasswordEqualTo(pwd);
		AdminUserModel user = adminUserRepository.findOne(e, AdminUserModel.class);
		
//		if(user == null){
//			throw new ApiValidationException(ApiCode.InvalidUserOrPassword);
//		}
		
		return user;
	}
	
//	public void validateImageToken(String token){
//		String benchmark = AppContext.getSession().get(AppConstants.Session.ImageToken);
//		if(!StringUtils.trimToEmpty(benchmark).equalsIgnoreCase(token)){
//			throw new ApiValidationException(ApiCode.InvalidToken);
//		}
//	}
}
