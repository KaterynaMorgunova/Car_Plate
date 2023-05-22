package com.f1dot5.dashboard;

import com.f1dot5.data.Article;
import com.f1dot5.data.CartArticle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class DashboardArticle {
    private boolean checked = false;
    private CartArticle cartArticle;
    private Article article;
}
