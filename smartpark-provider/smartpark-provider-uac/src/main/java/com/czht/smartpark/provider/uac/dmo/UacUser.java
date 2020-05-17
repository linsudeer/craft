package com.czht.smartpark.provider.uac.dmo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Table(name = "`tb_uac_user`")
public class UacUser {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户名", required = true)
    @Size(max = 100, message = "用户名长度不超过100")
    @Column(name = "`username`")
    private String username;

    @ApiModelProperty(value = "账号，登录账号", required = true)
    @Size(max = 100, message = "账号长度不超过100")
    @Column(name = "`account`")
    private String account;

    @ApiModelProperty(value = "系统显示名称")
    @Column(name = "`nickname`")
    private String nickname;

    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "`password`")
    private String password;

    @ApiModelProperty(value = "关联的部门ID", required = true)
    @Column(name = "org_id")
    private Integer orgId;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型", required = true)
    @Column(name = "`user_type`")
    private Integer userType;

    /**
     * 账户是否过期
     */
    @ApiModelProperty(value = "账号不过期（true-不过期；false-过期）")
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    /**
     * 账户是否被锁（冻结）
     */
    @ApiModelProperty(value = "账号不锁定（true-不锁定；false-锁定）")
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    /**
     * 账户凭证是否过期
     */
    @ApiModelProperty(value = "账号凭证过期（true-不过期；false-过期）")
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @ApiModelProperty(value = "是否可用（true-可用；false-不可用）")
    @Column(name = "`enabled`")
    private boolean enabled = true;

    @ApiModelProperty(value = "排序")
    @Column(name = "`seq`")
    private Integer seq;

    @Column(name = "`modify_time`")
    @ApiModelProperty(hidden = true)
    private Date modifyTime;

    @Column(name = "`create_time`")
    @ApiModelProperty(hidden = true)
    private Date createTime;


}