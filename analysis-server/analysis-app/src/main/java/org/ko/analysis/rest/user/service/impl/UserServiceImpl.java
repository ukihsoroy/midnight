package org.ko.analysis.rest.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.analysis.conf.api.ResponseCode;
import org.ko.analysis.conf.exp.BusinessException;
import org.ko.analysis.rest.user.condition.QueryUserCondition;
import org.ko.analysis.rest.user.dto.UserDTO;
import org.ko.analysis.rest.user.repository.UserRepository;
import org.ko.analysis.rest.user.service.UserService;
import org.ko.analysis.store.master.domain.Role;
import org.ko.analysis.store.master.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<UserDTO> queryUserList(QueryUserCondition<User> condition) {
        return userRepository.queryUserList(condition);
    }

    @Override
    public User queryUserInfo(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public Long createUser(User user) {
        //加密password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.insert(user) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return user.getId();
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        if (userRepository.updateById(user) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return user;
    }

    @Override
    public Long removeUser(Long id) {
        if (userRepository.deleteById(id) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.loadUserByUsername(username);
        if (userDTO != null) {
            List<String> roles = userDTO.getRoleDTOS().stream().map(Role::getCode).collect(Collectors.toList());
            userDTO.setRoles(roles);
            return userDTO;
        }
        throw new UsernameNotFoundException("用户不存在");
    }
}
