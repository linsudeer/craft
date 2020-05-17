package com.czht.smartpark.provider.uac.dmo;

import javax.persistence.*;

@Table(name = "`tb_uac_client_resource`")
public class UacClientResource {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户端id
     */
    @Column(name = "`client_id`")
    private Integer clientId;

    /**
     * 资源id
     */
    @Column(name = "`resource_id`")
    private Integer resourceId;

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
     * 获取客户端id
     *
     * @return client_id - 客户端id
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * 设置客户端id
     *
     * @param clientId 客户端id
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取资源id
     *
     * @return resource_id - 资源id
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id
     *
     * @param resourceId 资源id
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}