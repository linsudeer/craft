package com.czht.smartpark.auth.service;

import com.czht.smartpark.auth.dmo.Role;
import com.czht.smartpark.common.core.support.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID查找对应的角色信息
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(Integer userId);
}
