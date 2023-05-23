package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Customer {
    private Date createdAt = new Date();
    private String name;
    private String password;
    private String phone;
    private String email;
}
