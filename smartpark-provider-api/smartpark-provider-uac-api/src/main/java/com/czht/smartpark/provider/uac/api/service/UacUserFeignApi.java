package com.czht.smartpark.provider.uac.api.service;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.feign.config.FeignClientConfig;
import com.czht.smartpark.provider.uac.api.service.hystrix.UacUserApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "smartpark-provider-uac", configuration = FeignClientConfig.class, fallback = UacUserApiHystrix.class)
public interface UacUserFeignApi {

    @GetMapping("/user/getUserByName")
    ResultTip getUserByName(@RequestParam("name") String name);


}
