package com.czht.smartpark.provider.uac.dto;

import com.czht.smartpark.provider.uac.dmo.UacMenu;
import com.czht.smartpark.provider.uac.dmo.UacRole;
import com.czht.smartpark.provider.uac.dmo.UacUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户，组合有用户的权限信息
 */
@Setter
@Getter
public class UacUserDto extends UacUser {


    /**
     * 用户拥有的角色
     */
    private List<UacRole> roles;

    /**
     * 用户拥有的菜单
     */
    private List<UacMenu> menus;




}
