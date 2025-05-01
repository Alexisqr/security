package edu.oleks.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
@author   oleksandra
@project   security
@class  AuthenticationResponse
@version  1.0.0
@since 01.05.2025 - 14.10
*/
@Builder
@Getter
@Setter

public class AuthenticationResponse
{

    private String token;


    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public static AuthenticationResponse of(String token) {
        return new AuthenticationResponse(token);
    }
}
