package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacUserRole;

public interface UacUserRoleService {
    int add(UacUserRole uacUserRole);

    int delByUserId(Integer userId);
}
