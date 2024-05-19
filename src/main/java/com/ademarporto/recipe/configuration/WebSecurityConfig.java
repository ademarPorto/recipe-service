package com.ademarporto.recipe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String ACTUATOR_ENDPOINT = "/actuator/**";
    private static final String SWAGGER_ENDPOINT = "/swagger-ui.html";
    private static final String SWAGGER_API_DOC_ENDPOINT = "/v3/api-docs";
    private static final String SWAGGER_CONFIGURATION_ENDPOINT = "/configuration/ui";
    private static final String SWAGGER_RESOURCE_ENDPOINT = "/swagger-resources/**";
    private static final String SWAGGER_CONFIGURATION_SECURITY_ENDPOINT = "/configuration/security";
    private static final String SWAGGER_WEBJAR_ENDPOINT = "/webjars/**";
    private static final String CSRF_ENDPOINT = "/csrf";
    private static final String ROOT_ENDPOINT = "/v1/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest()
                        .permitAll());

        return http.build();
    }

}
