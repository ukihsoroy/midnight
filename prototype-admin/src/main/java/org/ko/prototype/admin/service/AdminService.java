package org.ko.prototype.admin.service;

import org.ko.prototype.admin.dao.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor=Throwable.class)
public class AdminService {

	private static final Logger log = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
    private ValidationService validationService;
	@Autowired
    private AdminUserRepository adminUserRepository;
	
	/**
	 * 登录
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 */
	public View<AdminUserModel> login(String username, String password, String imageToken, String lastLoginIp) throws Exception {
		View<AdminUserModel> view = new View<>();
		AdminUserModel user = validationService.assureAdminUserExists(username, password);
		user.setLastLoginTime(new Date());
		user.setLastLoginIp(lastLoginIp);
		adminUserRepository.updateById(user);

		postLogin(user);

		view.setModel(user);

		return view;
	}

	public View logout(){
		return new View();
	}

	protected void postLogin(AdminUserModel user) throws Exception {
		AuthenticationBean profile = buildUserProfile(user);
	}

	protected AuthenticationBean buildUserProfile(AdminUserModel user) throws Exception {
		AuthenticationBean profile = new AuthenticationBean();

		profile.setId(user.getId().longValue());
		profile.setUsername(user.getLoginName());
		profile.setRole(user.getRoleId().shortValue());

		return profile;
	}
}
