package org.ko.analysis.rest.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.analysis.rest.user.condition.QueryUserCondition;
import org.ko.analysis.rest.user.dto.UserDTO;
import org.ko.analysis.store.master.domain.User;

public interface UserService extends IService<User> {

    /**
     * <p>查询用户列表</p>
     * @param condition
     * @return
     */
    IPage<UserDTO>  queryUserList(QueryUserCondition<User> condition);

    /**
     * <p>查询用户详细</p>
     * @param id
     * @return
     */
    User queryUserInfo(Long id);

    /**
     * <p>创建用户</p>
     * @param User
     * @return
     */
    Long createUser(User User);

    /**
     * <p>更新用户信息</p>
     * @param id
     * @param User
     * @return
     */
    User updateUser(Long id, User User);


    /**
     * <p>通过ID删除用户</p>
     * @param id
     * @return
     */
    Long removeUser(Long id);

}
