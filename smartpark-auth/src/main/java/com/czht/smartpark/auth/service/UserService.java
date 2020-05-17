package com.czht.smartpark.auth.service;

import com.czht.smartpark.auth.dmo.User;

import java.util.Set;

public interface UserService {

    User getByAccount(String name);

    /**
     * 查找用户的菜单权限
     * @param userId
     * @return
     */
    Set<String> getUserMenuAuth(Integer userId);

    /**
     * 查找客户端资源权限
     * @param clientId
     * @return
     */
    Set<String> getClientResourceAuth(String clientId);

}
