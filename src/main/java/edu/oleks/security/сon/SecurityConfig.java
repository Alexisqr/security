package edu.oleks.security.сon;

/*
@author   oleksandra
@project   security
@class  SecurityConfig
@version  1.0.0
@since 21.03.2025 - 10.59
*/


import org.springframework.aop.Advisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static Advisor preAuthorizeMethodInterceptor(){
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests( req ->
                        req.requestMatchers("/index.html").permitAll()
                                //.requestMatchers("/api/v1/animals/hello/admin").hasRole("ADMIN")
                                //.requestMatchers("/api/v1/animals/location/{location}").hasAnyRole("USER", "ADMIN","SUPERADMIN")
                                //.requestMatchers("/api/v1/animals/sex/{sex}").hasAnyRole("USER", "ADMIN","SUPERADMIN")
                                //.requestMatchers(HttpMethod.POST, "/api/v1/animals").hasAnyRole( "ADMIN","SUPERADMIN")
                                //.requestMatchers(HttpMethod.PUT, "/api/v1/animals/{id}").hasAnyRole( "ADMIN","SUPERADMIN")
                                //.requestMatchers(HttpMethod.DELETE, "/api/v1/animals/del/{id}").hasAnyRole( "SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/animals").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/animals/{id}").permitAll()
                                //.requestMatchers("/api/v1/animals/hello/user").hasRole("USER")
                                .requestMatchers("/api/v1/animals/hello/unknown").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode("superadmin"))
                .roles("SUPERADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }


}