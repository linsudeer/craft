package com.czht.smartpark.provider.uac.dmo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`tb_uac_resource`")
public class UacResource {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源名称
     */
    @Column(name = "`resource_name`")
    private String resourceName;

    /**
     * 资源地址
     */
    @Column(name = "`resource_url`")
    private String resourceUrl;

    /**
     * 资源所属系统
     */
    @Column(name = "`client_id`")
    private Integer clientId;

    /**
     * 资源分类
     */
    @Column(name = "`resource_class`")
    private Integer resourceClass;

    /**
     * 资源所属编码
     */
    @Column(name = "`resource_code`")
    private String resourceCode;

    @Column(name = "`seq`")
    private Integer seq;

    @Column(name = "`modify_time`")
    private Date modifyTime;

    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return resource_name - 资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称
     *
     * @param resourceName 资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * 获取资源地址
     *
     * @return resource_url - 资源地址
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置资源地址
     *
     * @param resourceUrl 资源地址
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    /**
     * 获取资源所属系统
     *
     * @return client_id - 资源所属系统
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * 设置资源所属系统
     *
     * @param clientId 资源所属系统
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取资源分类
     *
     * @return resource_class - 资源分类
     */
    public Integer getResourceClass() {
        return resourceClass;
    }

    /**
     * 设置资源分类
     *
     * @param resourceClass 资源分类
     */
    public void setResourceClass(Integer resourceClass) {
        this.resourceClass = resourceClass;
    }

    /**
     * 获取资源所属编码
     *
     * @return resource_code - 资源所属编码
     */
    public String getResourceCode() {
        return resourceCode;
    }

    /**
     * 设置资源所属编码
     *
     * @param resourceCode 资源所属编码
     */
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    /**
     * @return seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}