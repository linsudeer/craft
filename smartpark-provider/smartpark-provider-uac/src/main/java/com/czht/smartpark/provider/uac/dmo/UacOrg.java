package com.czht.smartpark.provider.uac.dmo;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(name = "`tb_uac_organization`")
public class UacOrg {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级id
     */
    @Column(name = "`parent_id`")
    private Integer parentId;

    /**
     * 组织层级
     */
    @Column(name = "`level`")
    private Integer level;

    @Size(max=50, message = "组织机构名称长度不超过50")
    @Column(name = "`org_name`")
    private String orgName;

    /**
     * 部门电话
     */
    @Size(max=50, message = "电话长度不超过50")
    @Column(name = "`org_tel`")
    private String orgTel;

    /**
     * 路径,部门id已 点 链接 1.2.3.
     */
    @Column(name = "`path`")
    private String path;

    /**
     * 是否启用
     */
    @Column(name = "`enabled`")
    private Boolean enabled;

    /**
     * 序号
     */
    @Column(name = "`seq`")
    private Integer seq;

    /**
     * 修改时间
     */
    @Column(name = "`modify_time`")
    private Date modifyTime;

    /**
     * 创建时间
     */
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
     * 获取父级id
     *
     * @return parent_id - 父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级id
     *
     * @param parentId 父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取组织层级
     *
     * @return level - 组织层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置组织层级
     *
     * @param level 组织层级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return org_name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * 获取部门电话
     *
     * @return org_tel - 部门电话
     */
    public String getOrgTel() {
        return orgTel;
    }

    /**
     * 设置部门电话
     *
     * @param orgTel 部门电话
     */
    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel == null ? null : orgTel.trim();
    }

    /**
     * 获取路径,部门id已 点 链接 1.2.3.
     *
     * @return path - 路径,部门id已 点 链接 1.2.3.
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径,部门id已 点 链接 1.2.3.
     *
     * @param path 路径,部门id已 点 链接 1.2.3.
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取是否启用
     *
     * @return enabled - 是否启用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用
     *
     * @param enabled 是否启用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取序号
     *
     * @return seq - 序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置序号
     *
     * @param seq 序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}