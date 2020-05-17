package com.czht.smartpark.auth.dmo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`tb_uac_role`")
public class Role implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max=50, message = "角色Key名称长度不超过50")
    @Column(name = "role_key")
    private String roleKey;

    @Size(max=50, message = "角色名称长度不超过50")
    @Column(name = "`role_name`")
    private String roleName;

    @Column(name = "`description`")
    private String description;

    @Column(name = "`seq`")
    private Integer seq;

    @Column(name = "`modify_time`")
    private Date modifyTime;

    @Column(name = "`create_time`")
    private Date createTime;
}