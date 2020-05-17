### Feign 客户端权限验证

采用Feign调用微服务的权限验证有两种方式
1. 通过配置Feign拦截器在Feign客户端调用头信息增加传递过来的Access_token
2. 使用Oauth2 中的 （client_credentials）客户端模式

这里采用第二种方式，因为第一种方式局限于调用时必须传递access_token，Oauth2的客户端模式，可自己生成acess_token验证。

Oauth2FeignClient 配置

```aidl
/*
 * Copyright (c) 2018. smartpark.net All Rights Reserved.
 * 项目名称：smartpark快速搭建企业级分布式微服务平台
 * 类名称：OAuth2FeignAutoConfiguration.java
 * 创建人：刘兆明
 * 联系方式：smartpark.net@gmail.com
 * 开源地址: https://github.com/smartpark
 * 博客地址: http://blog.smartpark.net
 * 项目官网: http://smartpark.net
 */

package com.czht.smartpark.common.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.List;

/**
 * The class O auth 2 feign auto configuration.
 *
 * @author smartpark.net @gmail.com
 */
@Configuration
public class OAuth2FeignAutoConfiguration {

	@Autowired
	private Oauth2ClientProperties oauth2ClientProperties;

	/**
	 * Resource details client credentials resource details.
	 *
	 * @return the client credentials resource details
	 */
	@Bean
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
		details.setId(oauth2ClientProperties.getId());
		details.setAccessTokenUri(oauth2ClientProperties.getAccessTokenUri());
		details.setClientId(oauth2ClientProperties.getClientId());
		details.setClientSecret(oauth2ClientProperties.getClientSecret());
		List<String> scops = new ArrayList<>();
		details.setAuthenticationScheme(AuthenticationScheme.header);
		details.setClientAuthenticationScheme(AuthenticationScheme.header);
		scops.add(oauth2ClientProperties.getScope());
		details.setScope(scops);
		return details;
	}

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(){
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
	}

	@Bean
	public OAuth2RestTemplate clientCredentialsRestTemplate() {
		return new OAuth2RestTemplate(clientCredentialsResourceDetails());
	}

	/**
	 * Feign logger level logger . level.
	 *
	 * @return the logger . level
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	/*
	 * To disable Hystrix support on a per-client basis create a vanilla Feign.Builder with the "prototype" scope, e.g.:
	 */
//	@Bean
//	@Scope("prototype")
//	public Feign.Builder feignBuilder() {
//		return Feign.builder();
//	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new Oauth2FeignErrorInterceptor();
	}
}
```

oauth2ClientProperties.java
```aidl
/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：Oauth2ClientProperties.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.czht.smartpark.common.feign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The class Oauth 2 client properties.
 *
 * @author paascloud.net @gmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
public class Oauth2ClientProperties {
	private String id;
	private String accessTokenUri;
	private String clientId;
	private String clientSecret;
	private String clientAuthenticationScheme;
	private String scope;
}


```


Oauth2FeignErrorInterceptor.java

```aidl
/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：Oauth2FeignErrorInterceptor.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.czht.smartpark.common.feign.config;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;


/**
 * The class Feign error interceptor.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
public class Oauth2FeignErrorInterceptor implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();

	/**
	 * Decode exception.
	 *
	 * @param methodKey the method key
	 * @param response  the response
	 *
	 * @return the exception
	 */
	@Override
	public Exception decode(final String methodKey, final Response response) {
		if (response.status() >= HttpStatus.BAD_REQUEST.value() && response.status() < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return new HystrixBadRequestException("request exception wrapper");
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			HashMap map = mapper.readValue(response.body().asInputStream(), HashMap.class);
			Integer code = (Integer) map.get("code");
			String message = (String) map.get("message");
			if (code != null) {
				ErrorCode anEnum = ErrorCode.getEnum(code);
				if (anEnum != null) {
					if (anEnum == ErrorCode.ARG_ILLEGAL_ERROR) {
						throw new IllegalArgumentException(message);
					} else {
						throw new BusinessException(anEnum);
					}
				} else {
					throw new BusinessException(ErrorCode.ARG_ILLEGAL_ERROR, message);
				}
			}
		} catch (IOException e) {
			log.info("Failed to process response body");
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}
}

```

注意：采用Feign嗲用其他微服务的时候需要保证 服务之间的想要和接收格式一致。例如，如果服务想要采用fastjson的想要格式，客户端一定要一致。因为这个问题曾导致调用服务一直验证失败。