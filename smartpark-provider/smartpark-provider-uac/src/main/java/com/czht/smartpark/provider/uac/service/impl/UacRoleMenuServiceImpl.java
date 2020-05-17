package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.provider.uac.dmo.UacRoleMenu;
import com.czht.smartpark.provider.uac.mapper.UacRoleMenuMapper;
import com.czht.smartpark.provider.uac.service.UacRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UacRoleMenuServiceImpl implements UacRoleMenuService {

    @Resource
    private UacRoleMenuMapper uacRoleMenuMapper;

    @Override
    public int delByRoleId(Integer roleId) {
        return uacRoleMenuMapper.delete(new UacRoleMenu(roleId));
    }

    @Override
    public int addRoleMenu(UacRoleMenu uacRoleMenu) {
        return uacRoleMenuMapper.insert(uacRoleMenu);
    }
}
