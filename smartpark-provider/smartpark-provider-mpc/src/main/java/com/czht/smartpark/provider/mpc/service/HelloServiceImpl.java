package com.czht.smartpark.provider.mpc.service;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.provider.uac.api.service.UacUserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private UacUserFeignApi uacUserFeignApi;

    @Override
    public ResultTip getUserByName(String name) {
        return uacUserFeignApi.getUserByName(name);
    }
}
