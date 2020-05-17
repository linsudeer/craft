package com.czht.smartpark.common.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private boolean enable = true;

    @Value("${access-token-uri:http://localhost:8001/oauth/token}")
    private String accessTokenUrl;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.czht.smartpark"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("用户中心API")
                        .description("用户中心使用到的api")
                        .version("1.0")
//                        .license("Apache License 2.0")
//                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build()
                )
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(securityScheme()));
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(accessTokenUrl);

        return new OAuthBuilder()
                .name("spring-oauth2")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scope()))
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("spring-oauth2", scope())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scope() {
        return new AuthorizationScope[] {
                new AuthorizationScope("test", "swagger专用"),
        };
    }
}
