package com.czht.smartpark.provider.uac.api.service.hystrix;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.provider.uac.api.service.UacUserFeignApi;
import org.springframework.stereotype.Component;

@Component
public class UacUserApiHystrix implements UacUserFeignApi {
    @Override
    public ResultTip getUserByName(String name) {
        return ResultTip.success();
    }
}
