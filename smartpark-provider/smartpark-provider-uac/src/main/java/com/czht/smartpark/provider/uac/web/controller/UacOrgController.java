package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.provider.uac.dmo.UacOrg;
import com.czht.smartpark.provider.uac.service.UacOrgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门
 */
@RestController
@Api(tags = "组织机构")
@RequestMapping("/orgs")
public class UacOrgController {

    @Resource
    private UacOrgService uacOrgService;

    /**
     * 查找左右组织机构，列表
     * @return
     */
    @GetMapping("")
    public ResultTip getOrgs() {
        List<UacOrg> orgs = uacOrgService.getOrgs();
        return ResultTip.success(orgs);
    }

    /**
     * 根据组织机构id查找
     * @param orgId
     * @return
     */
    @GetMapping("/{orgId}")
    public ResultTip getOrgById(@PathVariable Integer orgId) {
        UacOrg org = uacOrgService.getOrgById(orgId);
        return ResultTip.success(org);
    }

    /**
     * 创建组织机构
     * @param org
     * @return
     */
    @PostMapping("")
    public ResultTip createOrg(UacOrg org) {
        int result = uacOrgService.createOrg(org);
        return ResultTip.success(result);
    }

    /**
     * 跟新组织机构，全量更新
     * @param orgId
     * @param org
     * @return
     */
    @PutMapping("")
    public ResultTip updateOrg(UacOrg org) {
        int result = uacOrgService.updateOrg(org);
        return ResultTip.success(result);
    }

    /**
     * 删除组织机构,如果需要批量删除需id已 ; 隔离
     * @param orgIds
     * @return
     */
    @DeleteMapping("/{orgIds}")
    public ResultTip delOrg(@PathVariable String orgIds) {
        int result = uacOrgService.delOrg(orgIds);
        return ResultTip.success(result);
    }
}
