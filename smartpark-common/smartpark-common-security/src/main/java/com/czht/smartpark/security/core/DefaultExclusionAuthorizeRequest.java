package com.czht.smartpark.security.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultExclusionAuthorizeRequest implements ExclusionAuthorizeRequest {

    @Override
    public String[] exclusion() {
        log.info("验证所有的权限资源");
        return new String[0];
    }
}
