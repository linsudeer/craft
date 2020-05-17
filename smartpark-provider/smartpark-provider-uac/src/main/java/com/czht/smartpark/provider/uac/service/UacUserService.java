package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacUser;
import com.czht.smartpark.provider.uac.dto.UacUserDto;

public interface UacUserService {

    /**
     * 创建用户信息
     * @param user
     */
    int createUser(UacUser user);

    /**
     * 跟新用户
     * @param account
     * @return
     */
    int updateUser(UacUser account);

    /**
     * 修改密码
     * @param userId
     * @param pwd
     * @return
     */
    int modifyPwd(Integer userId, String pwd);

    /**
     * 禁用
     * @param userIds
     * @return
     */
    int disableUser(String userIds);

    /**
     * 启用
     * @param userIds
     * @return
     */
    int enableUser(String userIds);

    /**
     * 是否逻辑删除
     * @param userIds
     * @param isDel true: 从数据库删除 false 逻辑删除
    * @return
     */
    int delUser(String userIds, boolean isDel);

    /**
     * 根号账号查找用户
     * @param s
     * @return
     */
    UacUserDto getByAccount(String s);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    int addRolesForUser(Integer userId, Integer[] roleIds);

    /**
     * 添加组织机构数据权限
     * @param userId
     * @param auth 对应的组织机构ID
     * @return
     */
    int addOrgAuthForUser(Integer userId, String[] auth);

}
