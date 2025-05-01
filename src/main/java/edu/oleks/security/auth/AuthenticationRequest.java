package edu.oleks.security.auth;

/*
@author   oleksandra
@project   security
@class  AuthenticotionRequest
@version  1.0.0
@since 01.05.2025 - 14.01

*/


import lombok.Data;
import lombok.NonNull;
@Data

public class AuthenticationRequest
{
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
