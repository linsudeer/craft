package com.czht.smartpark.provider.uac.web.rpc;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.provider.uac.api.service.UacUserFeignApi;
import com.czht.smartpark.provider.uac.dmo.UacUser;
import com.czht.smartpark.provider.uac.service.UacUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 开放API的实现
 */
@RefreshScope
@Api(tags = "用户（对外开放接口）")
@RestController
public class UacUserFeignClient implements UacUserFeignApi {

    @Resource
    private UacUserService uacUserService;

    @Override
    public ResultTip getUserByName(String name) {
        UacUser user = uacUserService.getByAccount(name);
        return ResultTip.success(user);
    }
}
