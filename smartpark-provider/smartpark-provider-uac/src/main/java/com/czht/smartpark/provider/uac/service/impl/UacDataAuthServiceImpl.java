package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.provider.uac.dmo.UacDataAuth;
import com.czht.smartpark.provider.uac.mapper.UacDataAuthMapper;
import com.czht.smartpark.provider.uac.service.UacDataAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UacDataAuthServiceImpl implements UacDataAuthService {

    @Resource
    private UacDataAuthMapper uacDataAuthMapper;

    @Override
    public int delByUserId(Integer userId) {
        return uacDataAuthMapper.delete(new UacDataAuth(userId));
    }

    @Override
    public int addOrgAuth(UacDataAuth uacDataAuth) {
        return uacDataAuthMapper.insert(uacDataAuth);
    }
}
