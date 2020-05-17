package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.provider.uac.dmo.UacClientDetails;
import com.czht.smartpark.provider.uac.service.UacClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 访问平台
 */
@RestController
@Api(tags = "平台")
@RequestMapping("/clients")
public class UacClientController {

    @Resource
    private UacClientDetailsService uacClientDetailsService;


    /**
     * 申请客户端
     *
     * @param clientDetails
     * @return
     */
    @ApiOperation(value = "申请")
    @PostMapping("")
    public ResultTip createClient(UacClientDetails clientDetails) {
        try {
            int result = uacClientDetailsService.createClient(clientDetails);
            return ResultTip.success(result);
        }catch (DuplicateKeyException e) {
            throw new BusinessException(ErrorCode.DUPLICATE_KEY, "客户端ID已存在");
        }
    }

    /**
     * 查所有客户端
     *
     * @return
     */
    @ApiOperation(value = "查找")
    @GetMapping("")
    public ResultTip getClients() {
        List<UacClientDetails> clients = uacClientDetailsService.listClientDetails();
        return ResultTip.success(clients);
    }

    /**
     * 根据id查客户端
     *
     * @param clientId
     * @return
     */
    @ApiOperation(value = "根据客户端ID查找")
    @GetMapping("/{clientId}")
    public ResultTip getClientById(@PathVariable String clientId) {
        try {
            UacClientDetails client = uacClientDetailsService.loadClientByClientId(clientId);
            return ResultTip.success(client);
        } catch (NoSuchClientException e) {
            return ResultTip.success();
        }
    }

    /**
     * 删除时，会同步删除所拥有的所有资源数据（慎重）
     * @param clientId
     * @return
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{clientId}")
    public ResultTip removeClientById(@PathVariable String clientId) {
        uacClientDetailsService.removeClientDetails(clientId);
        return ResultTip.success();
    }

    /**
     * 更新时，会同步更新和资源的关联关系表
     * @param clientId
     * @param client
     * @return
     */
    @ApiOperation(value = "更新（全量）")
    @PutMapping("")
    public ResultTip updateClientDetails(UacClientDetails client) {
        uacClientDetailsService.updateClientDetails(client);
        return ResultTip.success(client);
    }

    /**
     * 更新时，会同步更新与资源相关的管理关系表
     * @param clientId
     * @param clientSecret
     * @return
     */
    @ApiOperation(value = "更新")
    @PatchMapping("/{clientId}")
    public ResultTip updateClientSecret(@PathVariable String clientId, String clientSecret) {
        uacClientDetailsService.updateClientSecret(clientId, clientSecret);
        UacClientDetails client = uacClientDetailsService.loadClientByClientId(clientId);
        return ResultTip.success(client);
    }
}
