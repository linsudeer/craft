package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.provider.uac.dmo.UacMenu;
import com.czht.smartpark.provider.uac.service.UacMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单
 */
@RestController
@Api(tags = "菜单")
@RequestMapping("/menus")
public class UacMenuController {

    @Resource
    private UacMenuService uacMenuService;

    @ApiOperation(value = "查找菜单")
    @GetMapping("")
    public ResultTip getMenus() {
        List<UacMenu> menus = uacMenuService.getMenus();
        return ResultTip.success(menus);
    }

    @ApiOperation(value = "查找指定ID的菜单")
    @GetMapping("/{menuId}")
    public ResultTip getMenuById(@PathVariable Integer menuId) {
        UacMenu menu = uacMenuService.getMenuById(menuId);
        return ResultTip.success(menu);
    }

    @ApiOperation(value = "创建菜单")
    @PostMapping("")
    public ResultTip createMenu(UacMenu menu) {
        int result = uacMenuService.createMenu(menu);
        return ResultTip.success(result);
    }

    @ApiOperation(value = "修改菜单（全量）")
    @PutMapping("")
    public ResultTip updateMenu(UacMenu menu) {
        int result = uacMenuService.updateMenu(menu);
        return ResultTip.success(result);
    }

    /**
     * 删除菜单，如果多个菜单，则 ; 隔离
     * @param menuIds
     * @return
     */
    @ApiOperation(value = "删除菜单")
    @ApiImplicitParam(name = "menuIds", value = "菜单ID,多个用 ”;”分隔", example = "1;2;3", required = true)
    @DeleteMapping("/{menuIds}")
    public ResultTip delMenu(@PathVariable String menuIds) {
        try {
            int result = uacMenuService.delMenu(menuIds);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.DATA_INTEGRITY_VIOLATION.code(), "菜单已被绑定,请先解绑");
        }
    }


}
