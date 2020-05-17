package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacClientDetails;

import java.util.List;

public interface UacClientDetailsService {

    /**
     * 创建平台
     * @param clientDetails
     * @return
     */
    int createClient(UacClientDetails clientDetails);

    /**
     * 查找
     * @return
     */
    List<UacClientDetails> listClientDetails();

    UacClientDetails loadClientByClientId(String clientId);

    void removeClientDetails(String clientId);

    void updateClientSecret(String clientId, String clientSecret);

    void updateClientDetails(UacClientDetails client);
}
