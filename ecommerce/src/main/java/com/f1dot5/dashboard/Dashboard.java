package com.f1dot5.dashboard;

import com.f1dot5.auth.LoginUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Dashboard {
    private List<DashboardArticle> articles = new ArrayList<>();
    private LoginUser user;
}
