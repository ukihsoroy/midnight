package org.ko.analysis.rest.system.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import org.apache.commons.collections.CollectionUtils;
import org.ko.analysis.conf.api.ResponseCode;
import org.ko.analysis.conf.exp.BusinessException;
import org.ko.analysis.conf.exp.UniqueException;
import org.ko.analysis.rest.system.service.SystemService;
import org.ko.analysis.rest.user.dto.UserDTO;
import org.ko.analysis.rest.user.repository.UserRepository;
import org.ko.analysis.rest.user.repository.UserRoleRepository;
import org.ko.analysis.rest.user.service.UserService;
import org.ko.analysis.store.ads.domain.Role;
import org.ko.analysis.store.ads.domain.User;
import org.ko.analysis.store.ads.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);


    @Autowired
    private PasswordEncoder passwordEncoder;

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


    @Override
    public Long register(UserDTO userDTO, HttpServletRequest request) {

        //检查用户名，邮箱，手机是否重复
        validatorRegister(userDTO);
        
        Long userId = userService.createUser(map(userDTO));


        List<UserRole> userRoles;
        if (CollectionUtils.isNotEmpty(userDTO.getRoles())) {
            userRoles = userDTO.getRoleDTOS().stream().map(roleDTO -> {
                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleDTO.getCode());
                userRole.setUserId(userId);
                return userRole;
            }).collect(Collectors.toList());
        } else {
            userRoles = userDTO.getRoleDTOS().stream().map(roleDTO -> {
                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleDTO.getCode());
                userRole.setUserId(userId);
                return userRole;
            }).collect(Collectors.toList());
//                  userRoles =  Stream.of(RoleCodeEnum.ROLE_USER).map(roleDTO -> {
//                UserRole userRole = new UserRole();
//                userRole.setRoleCode(roleDTO.getCode());
//                userRole.setUserId(userId);
//                return userRole;
//            }).collect(Collectors.toList());
        }

        Long count = userRoleRepository.insertList(userRoles);

        if (count == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }

        registerSession(userDTO, request);

        return userId;
    }

    @Override
    public void validUserUnique(String column, String value) {
        List<User> users = userRepository.selectList(Condition.create().eq(column, value));
        if (!users.isEmpty()) {
            throw new UniqueException(column + "重复!");
        }
    }

    private void validatorRegister(UserDTO userDTO) {
        Integer countUsername = userRepository.selectCount(
                Condition.create().eq("username", userDTO.getUsername()));
        if (countUsername > 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }

        Integer countMobile = userRepository.selectCount(
                Condition.create().eq("mobile", userDTO.getMobile()));
        if (countMobile > 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }

        Integer countEmail = userRepository.selectCount(
                Condition.create().eq("email", userDTO.getEmail()));
        if (countEmail > 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }

    private void registerSession(UserDTO userDTO, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    /**
     * UserDTO mapTo User
     * @param userDTO
     * @return
     */
    private User map (UserDTO userDTO) {
        User user = new User();
        if (userDTO != null) {
            BeanUtils.copyProperties(userDTO, user);
        }
        return user;
    }
}
