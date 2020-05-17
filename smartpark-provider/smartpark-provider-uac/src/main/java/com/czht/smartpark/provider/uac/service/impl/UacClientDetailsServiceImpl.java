package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.exception.NotExistException;
import com.czht.smartpark.provider.uac.dmo.UacClientDetails;
import com.czht.smartpark.provider.uac.mapper.UacClientDetailsMapper;
import com.czht.smartpark.provider.uac.service.UacClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UacClientDetailsServiceImpl implements UacClientDetailsService {

    /**
     * token有效期
     */
    private static final Integer ACCESS_TOKEN_EXPIRED = 1800;

    /**
     * token 刷新有效期
     */
    private static final Integer REFRESH_TOKEN_VALIDITY = 2400;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UacClientDetailsMapper uacClientDetailsMapper;

    @Override
    public int createClient(UacClientDetails clientDetails) {
        clientDetails.setAccessTokenValidity(ACCESS_TOKEN_EXPIRED);
        clientDetails.setRefreshTokenValidity(REFRESH_TOKEN_VALIDITY);
        clientDetails.setCreateTime(new Date());
        return uacClientDetailsMapper.insertSelective(clientDetails);
    }

    @Override
    public List<UacClientDetails> listClientDetails() {
        return uacClientDetailsMapper.selectAll();
    }

    @Override
    public UacClientDetails loadClientByClientId(String clientId) {
        return uacClientDetailsMapper.selectOne(new UacClientDetails(clientId));
    }

    @Override
    public void removeClientDetails(String clientId) {
        uacClientDetailsMapper.delete(new UacClientDetails(clientId));
    }

    @Override
    public void updateClientSecret(String clientId, String clientSecret) {
        UacClientDetails client = this.loadClientByClientId(clientId);
        if(client == null) {
            throw new NotExistException("%s 客户端不存在", clientId);
        }
        client.setClientSecret(passwordEncoder.encode(clientSecret));
        client.setModifyTime(new Date());
    }

    @Override
    public void updateClientDetails(UacClientDetails client) {
        UacClientDetails dbClient = this.loadClientByClientId(client.getClientId());
        if(client == null) {
            throw new NotExistException("%s 客户端不存在", dbClient);
        }
        client.setModifyTime(new Date());
        uacClientDetailsMapper.updateByPrimaryKey(client);
    }
}
