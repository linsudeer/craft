package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.provider.uac.dmo.UacRole;
import com.czht.smartpark.provider.uac.service.UacRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "角色")
@RestController
@RequestMapping("/roles")
public class UacRoleController {

    @Resource
    private UacRoleService uacRoleService;

    @ApiOperation(value = "查找所有角色")
    @GetMapping("")
    public ResultTip getRoles() {
        List<UacRole> roles = uacRoleService.getRoles();
        return ResultTip.success(roles);
    }

    @ApiOperation(value = "创建角色")
    @PostMapping("")
    public ResultTip createRole(UacRole role) {
        int result = uacRoleService.createRole(role);
        return ResultTip.success(result);
    }

    @ApiOperation(value = "给角色添加菜单权限")
    @PostMapping("/{roleId}/menus")
    public ResultTip addMenus(@PathVariable Integer roleId, Integer[] menuIds) {
        int result = uacRoleService.addMenusForRole(roleId, menuIds);
        return ResultTip.success(result);
    }
}
