package edu.oleks.security.сonfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/*
@author   oleksandra
@project   security
@class  AuditionConfig
@version  1.0.0
@since 03.04.2025 - 11.28
*/
@EnableMongoAuditing
@Configuration
public class AuditionConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwarelmpl();
    }


}
