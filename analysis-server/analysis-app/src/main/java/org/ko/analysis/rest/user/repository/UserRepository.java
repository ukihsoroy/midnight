package org.ko.analysis.rest.user.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ko.analysis.rest.user.condition.QueryUserCondition;
import org.ko.analysis.rest.user.dto.UserDTO;
import org.ko.analysis.store.master.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseMapper<User> {

    /**
     * 通过用户名加载用户
     * @param username 用户名
     * @return
     */
    UserDTO loadUserByUsername(String username);

    /**
     * 通过手机号加载用户
     * @param mobile 手机号
     * @return
     */
    UserDTO loadUserByMobile(String mobile);

    /**
     * 查询用户列表
     * @param condition 搜索条件
     * @return
     */
    List<UserDTO> queryUserList(QueryUserCondition<User> condition);

}
