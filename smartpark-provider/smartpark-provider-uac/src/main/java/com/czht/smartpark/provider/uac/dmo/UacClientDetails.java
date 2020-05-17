package com.czht.smartpark.provider.uac.dmo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Table(name = "`oauth_client_details`")
public class UacClientDetails {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户端id
     */
    @Size(max=50, message = "客户端ID长度不超过50")
    @Column(name = "`client_id`")
    private String clientId;

    /**
     * 客户端密钥
     */
    @Size(max=50, message = "密钥长度不超过50")
    @Column(name = "`client_secret`")
    private String clientSecret;

    /**
     * 资源id
     */
    @Column(name = "`resource_ids`")
    private String resourceIds;

    /**
     * 作用域
     */
    @Column(name = "`scope`")
    private String scope;

    /**
     * 授权类型
     */
    @Column(name = "`authorized_grant_types`")
    private String authorizedGrantTypes;

    /**
     * 重定向地址
     */
    @Column(name = "`web_server_redirect_uri`")
    private String webServerRedirectUri;

    /**
     * 权限
     */
    @Column(name = "`authorities`")
    private String authorities;

    /**
     * token有限时间，单位秒
     */
    @Column(name = "`access_token_validity`")
    private Integer accessTokenValidity;

    /**
     * 刷新token有效时间，单位秒
     */
    @Column(name = "`refresh_token_validity`")
    private Integer refreshTokenValidity;

    /**
     * 额外的信息
     */
    @Column(name = "`additional_information`")
    private String additionalInformation;

    /**
     * 自动审核
     */
    @Column(name = "`autoapprove`")
    private Boolean autoapprove;

    /**
     * 是否启用
     */
    @Column(name = "`enabled`")
    private Boolean enabled;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`modify_time`")
    private Date modifyTime;


    public UacClientDetails() {}

    public UacClientDetails(String clientId) {
        this.clientId = clientId;
    }
}