package com.f1dot5.dashboard;
import com.f1dot5.auth.LoginUser;
import lombok.Data;

@Data
public class Cart {
    private final String id;
    private final DashboardArticle article;
}