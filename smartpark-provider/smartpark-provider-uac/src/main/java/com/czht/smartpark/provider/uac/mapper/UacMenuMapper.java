package com.czht.smartpark.provider.uac.mapper;

import com.czht.smartpark.provider.uac.dmo.UacMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UacMenuMapper extends Mapper<UacMenu> {
    List<UacMenu> getMenus();

    /**
     * 根据用户ID查找用户拥有的菜单权限
     * @param userId
     * @return
     */
    List<UacMenu> getMenusByUserId(Integer userId);
}