package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacRoleMenu;

public interface UacRoleMenuService {
    /**
     * 删除指定角色关联的菜单
     * @param roleId
     */
    int delByRoleId(Integer roleId);

    /**
     * 添加角色和菜单的关联关系
     * @param uacRoleMenu
     */
    int addRoleMenu(UacRoleMenu uacRoleMenu);
}
