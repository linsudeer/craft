package com.czht.smartpark.provider.uac.dmo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "`tb_uac_role_menu`")
public class UacRoleMenu {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`role_id`")
    private Integer roleId;

    @Column(name = "`menu_id`")
    private Integer menuId;

    public UacRoleMenu() {}

    public UacRoleMenu(Integer roleId) {
        this.roleId = roleId;
    }

    public UacRoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

}