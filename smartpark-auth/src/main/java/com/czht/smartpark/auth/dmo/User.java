package com.czht.smartpark.auth.dmo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "`tb_uac_user`")
public class User implements UserDetails {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100, message = "用户名长度不超过100")
    @Column(name = "`username`")
    private String username;

    @Size(max = 100, message = "账号长度不超过100")
    @Column(name = "`account`")
    private String account;

    @Column(name = "`nickname`")
    private String nickname;

    @Column(name = "`password`")
    private String password;

    @Column(name = "org_id")
    private Integer orgId;

    /**
     * 账户是否过期
     */
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    /**
     * 账户是否被锁（冻结）
     */
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    /**
     * 账户凭证是否过期
     */
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "`enabled`")
    private boolean enabled = true;

    @Column(name = "`seq`")
    private Integer seq;

    @Column(name = "`modify_time`")
    private Date modifyTime;

    @Column(name = "`create_time`")
    private Date createTime;

    @Transient
    private List<Role> roles = Collections.emptyList();

    @Transient
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();


}