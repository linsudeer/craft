package com.czht.smartpark.provider.uac.dmo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(name = "`tb_uac_menu`")
public class UacMenu {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父菜单ID")
    @Column(name = "`parent_id`")
    private Integer parentId;

    /**
     * 对应的业务子系统id
     */
    @ApiModelProperty(value = "对应的平台ID", required = true)
    @Column(name = "`app_id`")
    private Integer appId;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码", required = true)
    @Size(max=50, message = "菜单编码长度不超过50")
    @Column(name = "`menu_code`")
    private String menuCode;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    @Size(max=50, message = "菜单名称长度不超过50")
    @Column(name = "`menu_name`")
    private String menuName;

    /**
     * 菜单地址
     */
    @ApiModelProperty(value = "菜单地址")
    @Size(max=200, message = "菜单地址长度不超过50")
    @Column(name = "`menu_url`")
    private String menuUrl;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单对应的图标")
    @Column(name = "`menu_icon`")
    private String menuIcon;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @Column(name = "`seq`")
    private Integer seq;

    @ApiModelProperty(hidden = true)
    @Column(name = "`modify_time`")
    private Date modifyTime;

    @ApiModelProperty(hidden = true)
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
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取对应的业务子系统id
     *
     * @return app_id - 对应的业务子系统id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置对应的业务子系统id
     *
     * @param appId 对应的业务子系统id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取菜单编码
     *
     * @return menu_code - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单地址
     *
     * @return menu_url - 菜单地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单地址
     *
     * @param menuUrl 菜单地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取菜单图标
     *
     * @return menu_icon - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
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