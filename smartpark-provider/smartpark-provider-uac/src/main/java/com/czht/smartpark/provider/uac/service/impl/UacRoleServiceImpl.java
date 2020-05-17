package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.provider.uac.dmo.UacRole;
import com.czht.smartpark.provider.uac.dmo.UacRoleMenu;
import com.czht.smartpark.provider.uac.mapper.UacRoleMapper;
import com.czht.smartpark.provider.uac.service.UacRoleMenuService;
import com.czht.smartpark.provider.uac.service.UacRoleService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UacRoleServiceImpl implements UacRoleService {

    @Resource
    private UacRoleMapper uacRoleMapper;

    @Resource
    private UacRoleMenuService uacRoleMenuService;

    @Override
    public List<UacRole> getRolesByUserId(Integer userId) {
        return uacRoleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<UacRole> getRoles() {
        List<UacRole> roles = uacRoleMapper.selectAll();
        return roles;
    }

    @Override
    public int createRole(UacRole role) {
        role.setCreateTime(new Date());
        int result = uacRoleMapper.insertSelective(role);
        return result;
    }

    @Override
    public int addMenusForRole(Integer roleId, Integer[] menuIds) {
        int result = 0;
        Preconditions.checkArgument(menuIds != null && menuIds.length > 0, ErrorCode.ARGS_ERROR.msg());
        // 先删除关联关系
        uacRoleMenuService.delByRoleId(roleId);
        // 再新增关联关系
        for(Integer id : menuIds) {
            uacRoleMenuService.addRoleMenu(new UacRoleMenu(roleId, id));
        }
        return result;
    }
}
