package com.example.spring.config;

import com.example.spring.enums.RoleType;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.security.impl.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthTokenProvider tokenProvider;
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/swagger-ui/**", "/api-docs/**", "/h2/**").permitAll() // Swagger & DB Conn
                .antMatchers("/api/v1/accounts/login", "/api/v1/accounts/join").permitAll() // login && join
                .antMatchers(HttpMethod.GET, "/api/v1/posts/**", "/api/v1/commands/**").permitAll()
                .antMatchers("/api/v1/posts/**", "/api/v1/commands/**").authenticated()
                .antMatchers("/api/v1/accounts/**").authenticated()
                .antMatchers("/admin/**").hasAnyAuthority(RoleType.ADMIN.getRole())
                .anyRequest().authenticated().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
