package com.czht.smartpark.auth.service.impl;

import com.czht.smartpark.auth.dmo.Role;
import com.czht.smartpark.auth.mapper.RoleMapper;
import com.czht.smartpark.auth.service.RoleService;
import com.czht.smartpark.common.core.support.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Resource
    private RoleMapper uacRoleMapper;

    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return uacRoleMapper.getRolesByUserId(userId);
    }
}
