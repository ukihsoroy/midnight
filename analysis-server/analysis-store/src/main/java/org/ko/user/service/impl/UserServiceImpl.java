package org.ko.user.service.impl;

import org.ko.user.domain.User;
import org.ko.user.mapper.UserMapper;
import org.ko.user.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-09-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User > implements UserService {


    @Override
    public List< User > list () {
        return super.selectList( null );
    }

    @Override
    public User save ( User user ) {
        super.insert( user );
        System.err.println( 0 / 0 );
        return user;
    }
}
