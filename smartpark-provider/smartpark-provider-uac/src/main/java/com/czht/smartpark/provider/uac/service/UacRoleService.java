package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacRole;

import java.util.List;

public interface UacRoleService {

    /**
     * 根据用户ID查找对应的角色信息
     * @param userId
     * @return
     */
    List<UacRole> getRolesByUserId(Integer userId);

    /**
     * 查找所有角色
     * @return
     */
    List<UacRole> getRoles();

    /**
     * 创建角色
     * @param role
     * @return
     */
    int createRole(UacRole role);

    /**
     * 给角色添加菜单权限
     * @param roleId
     * @param menuIds
     * @return
     */
    int addMenusForRole(Integer roleId, Integer[] menuIds);
}
