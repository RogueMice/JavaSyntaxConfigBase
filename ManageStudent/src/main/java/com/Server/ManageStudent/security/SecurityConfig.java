package com.Server.ManageStudent.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(cs -> cs.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/addOrUpdate", "/api/auth/login").permitAll() //allow anonymous
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // don't use session because use Jwt
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable()); //turn off avoid return login html default
        return http.build();
    }
}
