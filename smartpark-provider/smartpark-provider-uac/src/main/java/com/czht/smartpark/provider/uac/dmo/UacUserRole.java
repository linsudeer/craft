package com.czht.smartpark.provider.uac.dmo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "`tb_uac_user_role`")
public class UacUserRole {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`role_id`")
    private Integer roleId;

    public UacUserRole() {
    }

    public UacUserRole(Integer userId) {
        this.userId = userId;
    }

    public UacUserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}