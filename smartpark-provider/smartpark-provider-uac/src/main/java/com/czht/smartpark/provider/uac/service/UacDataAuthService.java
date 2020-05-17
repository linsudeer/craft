package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacDataAuth;

public interface UacDataAuthService {
    /**
     * 删除用户对应的部门权限
     * @param userId
     */
    int delByUserId(Integer userId);

    /**
     * 增加用户对应的部门权限
     * @param uacUserRole
     * @return
     */
    int addOrgAuth(UacDataAuth uacDataAuth);
}
