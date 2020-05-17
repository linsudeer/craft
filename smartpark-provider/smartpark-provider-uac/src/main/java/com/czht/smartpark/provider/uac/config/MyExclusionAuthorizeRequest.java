package com.czht.smartpark.provider.uac.config;

import com.czht.smartpark.security.core.ExclusionAuthorizeRequest;
import org.springframework.stereotype.Component;

@Component
public class MyExclusionAuthorizeRequest implements ExclusionAuthorizeRequest {
    @Override
    public String[] exclusion() {
        String resources[] = {"/hello/**"};
        return resources;
    }
}
