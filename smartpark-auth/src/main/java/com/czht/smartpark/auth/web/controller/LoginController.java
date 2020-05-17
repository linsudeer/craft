package com.czht.smartpark.auth.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResultTip logout(HttpServletRequest request) {
        TokenExtractor extractor = new BearerTokenExtractor();
        Authentication authentication = extractor.extract(request);
        if(authentication == null) {
            return ResultTip.error(ErrorCode.UNAUTHORIZED);
        }
        Object token = authentication.getPrincipal();
        boolean isLogout = consumerTokenServices.revokeToken(String.valueOf(token));
        return ResultTip.success(isLogout);
    }
}
