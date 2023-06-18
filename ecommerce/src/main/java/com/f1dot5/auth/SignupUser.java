package com.f1dot5.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignupUser extends LoginUser {

    @Size(min=7, message="Phone must be at least 7 characters long")
    private String phone;

    @NotNull
    @Size(min=5, message="EMail must be at least 5 characters long")
    private String email;
}
