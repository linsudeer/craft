package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.provider.uac.dmo.UacUserRole;
import com.czht.smartpark.provider.uac.mapper.UacUserRoleMapper;
import com.czht.smartpark.provider.uac.service.UacUserRoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class UacUserRoleServiceImpl implements UacUserRoleService {

    @Resource
    private UacUserRoleMapper uacUserRoleMapper;

    @Override
    public int add(UacUserRole uacUserRole) {
        return uacUserRoleMapper.insert(uacUserRole);
    }

    @Override
    public int delByUserId(Integer userId) {
        return uacUserRoleMapper.delete(new UacUserRole(userId));
    }
}
