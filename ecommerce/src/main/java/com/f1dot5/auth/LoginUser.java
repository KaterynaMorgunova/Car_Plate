package com.f1dot5.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class LoginUser {

    @NotBlank(message="User name is required")
    @Size(min=3, message="User name must be at least 3 characters long")
    private String name;

    @NotBlank(message="User password is required")
    private String password;

    private String authenticationError;
}

