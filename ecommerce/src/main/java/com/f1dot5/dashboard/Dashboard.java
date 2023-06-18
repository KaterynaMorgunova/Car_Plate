package com.f1dot5.dashboard;

import com.f1dot5.auth.LoginUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Dashboard {
    private List<DashboardArticle> dashboardArticles = new ArrayList<>();

    private String error;

    public List<DashboardArticle> desiredArticles() {
        List<DashboardArticle> result = this.dashboardArticles.stream()
                .filter(x -> x.isChecked())
                .collect(Collectors.toList());
        return result;
    }
    private LoginUser user;
}
