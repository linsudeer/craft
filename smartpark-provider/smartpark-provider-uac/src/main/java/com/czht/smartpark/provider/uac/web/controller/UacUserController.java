package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.provider.uac.constant.UserStatus;
import com.czht.smartpark.provider.uac.dmo.UacUser;
import com.czht.smartpark.provider.uac.dto.UserModifyPwdDto;
import com.czht.smartpark.provider.uac.service.UacUserService;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * 用户
 */
@RestController
@Api(tags = "用户")
@RequestMapping("/users")
public class UacUserController {

    @Resource
    private UacUserService uacUserService;

//    @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
    @ApiOperation("根据用户名（账号）查找用户信息")
    @GetMapping("/{username}")
    public ResultTip getByUsername(@PathVariable String username) {
        UacUser user = uacUserService.getByAccount(username);
        return ResultTip.success(user);
    }

    /**
     * 获取当前登录用户
     * @param principal
     * @return
     */
    @ApiOperation("获取当前登录用户")
    @GetMapping("/current")
    public ResultTip getCurrentUser(Principal principal) {
        UacUser user =  uacUserService.getByAccount(principal.getName());
        return ResultTip.success(user);
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @ApiOperation("创建用户")
    @PostMapping(path = "")
    public ResultTip createUser(UacUser user) {
        Preconditions.checkArgument(user.getOrgId() != null, "部门不能为空");
        try {
            int result = uacUserService.createUser(user);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL, "所选部门不存在");
        }
    }

    /**
     * 更新用户（全量）
     * @param userId
     * @param user
     * @return
     */
    @ApiOperation("更新用户（全量）")
    @PutMapping("")
    public ResultTip updateUser(UacUser user) {
        Preconditions.checkArgument(user.getOrgId() != null, "部门不能为空");
        try {
            int result = uacUserService.updateUser(user);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL, "所选部门不存在");
        }
    }

    /**
     * 修改密码
     * @return
     */
    @ApiOperation("修改密码")
    @PatchMapping("/password/{userId}")
    public ResultTip modifyPwd(@PathVariable Integer userId, UserModifyPwdDto userModifyPwdDto) {
        String newPwd = userModifyPwdDto.getNewPassword();
        String oldPwd = userModifyPwdDto.getOldPassword();
        String confirmPwd = userModifyPwdDto.getConfirmPwd();

        Preconditions.checkArgument(!Strings.isNotEmpty(oldPwd), "原始密码不能为空");
        Preconditions.checkArgument(!Strings.isNotEmpty(newPwd), "新密码不能为空");
        Preconditions.checkArgument(!Strings.isNotEmpty(confirmPwd), "确认密码不能为空");
        Preconditions.checkArgument(!newPwd.equals(oldPwd), "原始密码和新密码不一致");

        int result = uacUserService.modifyPwd(userId, newPwd);
        return ResultTip.success(result);
    }

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/{userIds}")
    public ResultTip delUser(@PathVariable String userIds) {
        try {
            int result = uacUserService.delUser(userIds, false);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.DATA_INTEGRITY_VIOLATION, "用户已被绑定，请先解绑");
        }
    }

    /**
     * 更新用户状态
     * @param userIds
     * @return
     */
    @ApiOperation("更新用户状态")
    @PatchMapping("/status/{userIds}")
    public ResultTip disableUser(@PathVariable String userIds, String status) {
        Preconditions.checkArgument(!Strings.isNotEmpty(status), "缺少必要参数");
        int result = 0;
        if(UserStatus.ENABLED.getName().equals(status)) {
            result = uacUserService.enableUser(userIds);
        }else {
            result = uacUserService.disableUser(userIds);
        }
        return ResultTip.success(result);
    }

    @ApiOperation(value = "给用户分配角色")
    @PostMapping("/{userId}/roles")
    public ResultTip addRoles(@PathVariable Integer userId, @ApiParam(value = "角色ID", required = true) Integer[] roleIds) {
        int result = uacUserService.addRolesForUser(userId, roleIds);
        return ResultTip.success(result);
    }

    @ApiOperation(value = "给用户分配部门数据权限")
    @PostMapping("/{userId}/org-auth")
    public ResultTip addOrgAuth(@PathVariable Integer userId, @ApiParam(value = "部门ID", required = true) String[] orgIds) {
        int result = uacUserService.addOrgAuthForUser(userId, orgIds);
        return ResultTip.success(result);
    }

}
