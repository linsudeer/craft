package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.common.base.exception.NotExistException;
import com.czht.smartpark.provider.uac.constant.DataAuthType;
import com.czht.smartpark.provider.uac.dmo.*;
import com.czht.smartpark.provider.uac.dto.UacUserDto;
import com.czht.smartpark.provider.uac.mapper.UacUserMapper;
import com.czht.smartpark.provider.uac.service.*;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UacUserServiceImpl implements UacUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UacUserMapper uacUserMapper;

    @Resource
    private UacUserRoleService uacUserRoleService;

    @Resource
    private UacDataAuthService uacDataAuthService;

    @Resource
    private UacRoleService uacRoleService;

    @Resource
    private UacMenuService uacMenuService;


    @Override
    public int createUser(UacUser user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int result = uacUserMapper.insertSelective(user);
        return result;
    }

    @Override
    public int updateUser(UacUser user) {
        UacUser dbUser = uacUserMapper.selectByPrimaryKey(user.getId());
        if(dbUser == null) {
            throw new NotExistException("用户【%s】不存在", user.getUsername());
        }
        user.setModifyTime(new Date());
        return uacUserMapper.updateByPrimaryKey(user);
    }

    @Override
    public int modifyPwd(Integer userId, String pwd) {
        UacUser user = new UacUser();
        user.setId(userId);
        user.setPassword(pwd);
        return uacUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public int disableUser(String userIds) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isEmpty(userIds), ErrorCode.ARGS_NOT_EXIST.msg());
        String[] ids = userIds.split(";");
        try {
            if (ids.length > 1) {
                for (String id : ids) {
                    result += uacUserMapper.disableUser(NumberUtils.createInteger(id));
                }
            }else {
                result += uacUserMapper.disableUser(NumberUtils.createInteger(userIds.replace(";", "")));
            }
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
        }

        return result;
    }

    @Override
    @Transactional
    public int enableUser(String userIds) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isEmpty(userIds), ErrorCode.ARGS_NOT_EXIST.msg());
        String[] ids = userIds.split(";");
        try {
            if (ids.length > 1) {
                for (String id : ids) {
                    result += uacUserMapper.enableUser(NumberUtils.createInteger(id));
                }
            }else {
                result += uacUserMapper.enableUser(NumberUtils.createInteger(userIds.replace(";", "")));
            }
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
        }

        return result;
    }

    @Override
    @Transactional
    public int delUser(String userIds, boolean isDel) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isEmpty(userIds), ErrorCode.ARGS_NOT_EXIST.msg());
        if(isDel) {
            String[] ids = userIds.split(";");
            try {
                if (ids.length > 1) {
                    for (String id : ids) {
                        result += uacUserMapper.deleteByPrimaryKey(NumberUtils.createInteger(id));
                    }
                }else {
                    result += uacUserMapper.deleteByPrimaryKey(NumberUtils.createInteger(userIds.replace(";", "")));
                }
            } catch (NumberFormatException e) {
                throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
            }
        }else {
            result += this.disableUser(userIds);
        }
        return result;
    }


    @Override
    public UacUserDto getByAccount(String username) {
        UacUserDto userDto = new UacUserDto();
        UacUser user = uacUserMapper.getByAccount(username);
        if(user == null)
            throw new NotExistException("用户【%s】不存在", username);

        // 查找用户对应的角色
        List<UacRole> roles = uacRoleService.getRolesByUserId(user.getId());
        BeanUtils.copyProperties(user, userDto);
        userDto.setRoles(roles);
        // 查找用户对应的菜单权限
        List<UacMenu> menus = uacMenuService.getMenusByUserId(user.getId());
        userDto.setMenus(menus);
        // 查找用户的资源权限 (暂无需求)
        return userDto;
    }

    @Transactional
    @Override
    public int addRolesForUser(Integer userId, Integer[] roleIds) {
        int result = 0;
        Preconditions.checkArgument(roleIds != null && roleIds.length > 0, ErrorCode.ARGS_ERROR.msg());
        // 先删除用户对应的角色
        uacUserRoleService.delByUserId(userId);
        //这里再添加用户关系
        for(Integer id : roleIds) {
            result += uacUserRoleService.add(new UacUserRole(userId, id));
        }
        return result;
    }

    @Override
    public int addOrgAuthForUser(Integer userId, String[] auth) {
        int result = 0;
        Preconditions.checkArgument(auth != null && auth.length > 0, ErrorCode.ARGS_ERROR.msg());
        // 先删除用户对应的角色
        uacDataAuthService.delByUserId(userId);
        //这里再添加用户关系
        for(String id : auth) {
            result += uacDataAuthService.addOrgAuth(new UacDataAuth(userId, id, DataAuthType.ORGANIZATION.getName()));
        }
        return result;
    }
}
