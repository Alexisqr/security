package edu.oleks.security.security;

/*
@author   oleksandra
@project   security
@class  SecurityConfig
@version  1.0.0
@since 21.03.2025 - 10.59
*/


import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {


    private final AuthenticationProvider authenticationProvider;

    private final JwtFilter jwtAuthFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtFilter jwtAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static Advisor preAuthorizeMethodInterceptor(){
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(
                                        "/index.html",
                                        "/api/v1/auth/**",
                                        "/api/v1/auth/authenticate",
                                        "/api/v1/auth/register",
                                        "/sw.js"
                                ).permitAll()
                                .requestMatchers("/api/v1/auth/authenticate").permitAll()
                                .requestMatchers("/api/v1/animals/hello/unknown").permitAll()
                                .anyRequest().authenticated()
                )

                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}