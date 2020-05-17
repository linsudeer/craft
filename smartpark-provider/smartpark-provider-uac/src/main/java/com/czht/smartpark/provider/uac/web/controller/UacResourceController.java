package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.provider.uac.dmo.UacResource;
import com.czht.smartpark.provider.uac.service.UacResourceService;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源-API
 */
@RestController
@Api(tags = "系统API资源")
@RequestMapping("/resources")
public class UacResourceController {

    @Resource
    private UacResourceService uacResourceService;

    /**
     * 查找资源列表
     * @return
     */
    @ApiOperation(value = "查找资源")
    @GetMapping("")
    public ResultTip getResources() {
        List<UacResource> resources = uacResourceService.getResources();
        return ResultTip.success(resources);
    }

    /**
     * 根据Id查资源
     * @param resourceId
     * @return
     */
    @ApiOperation(value = "查找指定ID资源")
    @GetMapping("/{resourceId}")
    public ResultTip getResourceById(@PathVariable Integer resourceId) {
        UacResource resource = uacResourceService.gerResourceById(resourceId);
        return ResultTip.success(resource);
    }

    /**
     * 创建资源
     * @param resource
     * @return
     */
    @ApiOperation(value = "创建资源")
    @PostMapping("")
    public ResultTip createResource(UacResource resource) {
        Preconditions.checkArgument(resource.getClientId() != null, "资源所属客户端不能为空");
        try {
            int result = uacResourceService.createResource(resource);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.DATA_INTEGRITY_VIOLATION, "所选客户端不存在");
        }
    }

    /**
     * 更新资源
     * @param resourceId
     * @param resource
     * @return
     */
    @ApiOperation(value = "更新资源（全量）")
    @PutMapping("")
    public ResultTip updateResource(UacResource resource) {
        Preconditions.checkArgument(resource.getClientId() != null, "资源所属客户端不能为空");
        try {
            int result = uacResourceService.updateResource(resource);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(ErrorCode.DATA_INTEGRITY_VIOLATION, "所选客户端不存在");
        }
    }

    /**
     * 删除资源
     * @param resourceIds
     * @return
     */
    @ApiOperation(value = "删除资源，如果要删除多个，需要以英文分号分隔。例如：1;2;3")
    @DeleteMapping("/{resourceIds}")
    public ResultTip delResource(@PathVariable String resourceIds) {
        try {
            int result = uacResourceService.delResource(resourceIds);
            return ResultTip.success(result);
        }catch (DataIntegrityViolationException e){
            throw new BusinessException(ErrorCode.DATA_INTEGRITY_VIOLATION, "资源已被绑定，请先解绑");
        }
    }

    
}
