package edu.oleks.security.сonfig;

/*
@author   oleksandra
@project   security
@class  AuditorAwarelmpl
@version  1.0.0
@since 03.04.2025 - 11.17
*/

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwarelmpl  implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
