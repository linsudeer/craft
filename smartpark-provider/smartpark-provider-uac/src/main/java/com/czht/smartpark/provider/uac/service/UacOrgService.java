package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacOrg;

import java.util.List;

public interface UacOrgService {
    /**
     * 查找所有组织机构，列表
     * @return
     */
    List<UacOrg> getOrgs();

    /**
     * 根据组织机构id查找
     * @param orgId
     * @return
     */
    UacOrg getOrgById(Integer orgId);

    /**
     * 创建组织结构
     * @param org
     * @return
     */
    int createOrg(UacOrg org);

    /**
     * 跟新组织机构Id
     * @param orgId
     * @param org
     * @return
     */
    int updateOrg(UacOrg org);

    /**
     * 删除组织机构
     * @param orgIds
     * @return
     */
    int delOrg(String orgIds);
}
