package com.czht.smartpark.security.core;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 处理资源权限配置
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private List<AccessDecisionVoter> decisionVoters;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        String requestUrl = ((FilterInvocation) o).getRequest().getMethod() + ((FilterInvocation) o).getRequest().getRequestURI();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority grantedAuthority : authorities) {
            if(grantedAuthority.getAuthority().equals(requestUrl)) {
                return;
            }
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                return;
            }
        }
//        throw new AccessDeniedException("没有权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        for(AccessDecisionVoter voter : this.decisionVoters) {
            if(voter.supports(configAttribute)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        for(AccessDecisionVoter voter : this.decisionVoters) {
            if(!voter.supports(aClass)) {
                return false;
            }
        }
        return true;
    }
}
