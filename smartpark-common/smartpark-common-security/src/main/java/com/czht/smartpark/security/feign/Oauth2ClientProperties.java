package com.czht.smartpark.security.feign;

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

