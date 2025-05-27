package com.theanh1301.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // no dc enable san roi -> khong bat cung duoc
public class SecurityConfig {

    private final String [] PUBLIC_ENDPOINTS = {"/api/users", "/api/auth/login" ,"/api/auth/introspect"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).
                        permitAll().anyRequest().authenticated());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
