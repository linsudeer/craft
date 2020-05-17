package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacMenu;

import java.util.List;

public interface UacMenuService{
    /**
     * 查所有菜单
     * @return
     */
    List<UacMenu> getMenus();

    /**
     * 根据ID查菜单
     * @param menuId
     * @return
     */
    UacMenu getMenuById(Integer menuId);

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    int createMenu(UacMenu menu);

    /**
     * 更新菜单
     * @param menuId
     * @param menu
     * @return
     */
    int updateMenu(UacMenu menu);

    /**
     * 删除菜单
     * @param menuIds
     * @return
     */
    int delMenu(String menuIds);

    /**
     * 根据用户ID查找该用户对应的菜单
     * @param userId
     * @return
     */
    List<UacMenu> getMenusByUserId(Integer userId);
}
