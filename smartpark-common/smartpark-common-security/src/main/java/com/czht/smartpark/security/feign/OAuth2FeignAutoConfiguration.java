package com.czht.smartpark.security.feign;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The class O auth 2 feign auto configuration.
 *
 * @author smartpark.net @gmail.com
 */
@Configuration
public class OAuth2FeignAutoConfiguration {

	@Resource
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

}