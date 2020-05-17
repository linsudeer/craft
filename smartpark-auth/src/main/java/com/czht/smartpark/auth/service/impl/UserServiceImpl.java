package com.czht.smartpark.auth.service.impl;

import com.czht.smartpark.auth.dmo.User;
import com.czht.smartpark.auth.mapper.UserMapper;
import com.czht.smartpark.auth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper uacUserMapper;

    @Override
    public User getByAccount(String name) {
        User user = uacUserMapper.getByAccount(name);

        return user;
    }

    @Override
    public Set<String> getUserMenuAuth(Integer userId) {
        return uacUserMapper.getUserMenuAuth(userId);
    }

    @Override
    public Set<String> getClientResourceAuth(String clientId) {
        return uacUserMapper.getClientResourceAuth(clientId);
    }

}
