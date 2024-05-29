package com.alevilla86.firebase.security.test.firebase_security_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
        //Authentication.
        "/auth/**",
        "/test/**",

        //swagger ui
        "/v3/api-docs/**",
        "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(securityContext -> securityContext.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .httpBasic(Customizer.withDefaults());

        //Firebase Authentication
        http.oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));

        return http.build();
    }


}
