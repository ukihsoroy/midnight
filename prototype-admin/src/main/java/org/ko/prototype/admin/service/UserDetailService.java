package org.ko.prototype.admin.service;

import org.ko.prototype.admin.bean.domain.AdminUserModel;
import org.ko.prototype.admin.dao.repository.AdminUserRepository;
import org.ko.prototype.data.master.domain.bean.AdminUserExample;
import org.ko.prototype.data.master.domain.constants.AdminUserConstants;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService //implements UserDetailsService
{

    @Autowired private AdminUserRepository adminUserRepository;

//    @Override
//    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
//        AdminUserModel user = null;
//        AdminUserExample aue = new AdminUserExample();
//        aue.createCriteria()
//                .andLoginNameEqualTo(loginName)
//                .andDeleteStatusEqualTo(AdminUserConstants.Values.DeleteStatus.Available);
//        try {
//            user = adminUserRepository.findOne(aue, AdminUserModel.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

}
