package com.czht.smartpark.provider.uac.dmo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "`tb_uac_data_auth`")
public class UacDataAuth {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 对应的权限，目前对应的是ID。
     * 这里使用 String 类型是因为可能之后增加的权限是字符串代表。
     */
    @Column(name = "`auth`")
    private String auth;

    /**
     * 权限类型，目前对应的是组织机构，org
     */
    @Column(name = "`type`")
    private String type;

    public UacDataAuth() {}

    public UacDataAuth(Integer userId) {
        this.userId = userId;
    }

    public UacDataAuth(Integer userId, String auth, String type) {
        this.userId = userId;
        this.auth = auth;
        this.type = type;
    }
}