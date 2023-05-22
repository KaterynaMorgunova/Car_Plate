package com.f1dot5.data;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Date createdAt = new Date();
    private String name;
    private String password;
    private String phone;
    private String email;
}
