package com.czht.smartpark.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] NOT_AUTH_REQUEST = {"/oauth/**", "/actuator/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**"};

    @Autowired
    private ExclusionAuthorizeRequest exclusionAuthorizeRequest;

    @Autowired
    @Qualifier("myAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("myAccessDecisionManager")
    private AccessDecisionManager accessDecisionManager;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(getNotAuthRequest())
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
//                .and()
//                .authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {// 重写权限验证拦截器
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setAccessDecisionManager(accessDecisionManager);
//                        return object;
//                    }
//                })
                .and()
                .httpBasic();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 仅当ExclusionAuthorizeResource 不存在时才会实例化这个Bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ExclusionAuthorizeRequest.class)
    public ExclusionAuthorizeRequest exclusionAuthorizeResource() {
        return new DefaultExclusionAuthorizeRequest();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] getNotAuthRequest() {
        List<String> r = new ArrayList<>();
        r.addAll(Arrays.asList(NOT_AUTH_REQUEST));
        r.addAll(Arrays.asList(exclusionAuthorizeRequest.exclusion()));
        return r.toArray(new String[r.size()]);

    }
}
