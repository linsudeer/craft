package com.czht.smartpark.auth.service.security;

import com.czht.smartpark.auth.dmo.Role;
import com.czht.smartpark.auth.dmo.User;
import com.czht.smartpark.auth.service.RoleService;
import com.czht.smartpark.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserService uacUserService;

    @Resource
    private RoleService uacRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = uacUserService.getByAccount(username);
        if(user == null)
            throw new UsernameNotFoundException(String.format("用户%s不存在", username));

        List<Role> roles = uacRoleService.getRolesByUserId(user.getId());
        user.setRoles(roles);

        return user;
    }

}
