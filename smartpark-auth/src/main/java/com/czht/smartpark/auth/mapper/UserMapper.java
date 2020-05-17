package com.czht.smartpark.auth.mapper;

import com.czht.smartpark.auth.dmo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface UserMapper extends Mapper<User> {

    /**
     * 根据账号查找用户
     * @param name
     * @return
     */
    User getByAccount(@Param("account") String name);

    /**
     * 查找用户菜单权限
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