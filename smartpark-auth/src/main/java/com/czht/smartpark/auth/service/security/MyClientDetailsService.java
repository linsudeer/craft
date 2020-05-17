package com.czht.smartpark.auth.service.security;

import com.czht.smartpark.auth.service.UserService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端
 */
@Component
public class MyClientDetailsService implements UserDetailsService {

    @Resource
    @Qualifier("jdbcClientDetailsService")
    private ClientDetailsService clientDetailsService;
    private String emptyPassword = "";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails;
        try {
            clientDetails = clientDetailsService.loadClientByClientId(username);
        } catch (NoSuchClientException e) {
            throw new UsernameNotFoundException(String.format("客户端【%s】不存在", username));
        }
        String clientSecret = clientDetails.getClientSecret();
        if (clientSecret== null || clientSecret.trim().length()==0) {
            clientSecret = passwordEncoder.encode(emptyPassword);
        }
        // 查找对应的资源权限
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<String> resources = userService.getClientResourceAuth(username);
        if(!CollectionUtil.isEmpty(authorities)) {
            Iterator<String> it = resources.iterator();
            while(it.hasNext()) {
                authorities.add(new SimpleGrantedAuthority(it.next()));
            }
        }

        return new User(username, clientSecret, authorities);
    }
}
