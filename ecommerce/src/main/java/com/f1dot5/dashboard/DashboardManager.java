package com.f1dot5.dashboard;

import com.f1dot5.data.Article;
import com.f1dot5.data.CartArticle;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DashboardManager {

    public static List<CartArticle> ALL_CART_ARTICLES = Arrays.asList(
            new CartArticle(1L, 1, null),
            new CartArticle(2L, 1, null),
            new CartArticle(3L, 1, null),
            new CartArticle(4L, 1, null),
            new CartArticle(5L, 1, null)
    );

    public static List<Article> ALL_ARTICLES = Arrays.asList(
            new Article(1L, new Date(), "Article 1", "Desc 1", "/images/logo.png", 1.0f, "USD", 1),
            new Article(2L, new Date(), "Article 2", "Desc 2", "/images/logo.png", 2.0f, "USD", 2),
            new Article(3L, new Date(), "Article 3", "Desc 3", "/images/logo.png", 3.0f, "USD", 3),
            new Article(4L, new Date(), "Article 4", "Desc 4", "/images/logo.png", 4.0f, "USD", 4),
            new Article(5L, new Date(), "Article 5", "Desc 5", "/images/logo.png", 5.0f, "USD", 5)
    );

    private static Article getArticleById(Long id) {
        return DashboardManager.ALL_ARTICLES.stream().filter(x -> x.getId().equals(id)).findFirst().get();
    }

    public static List<DashboardArticle> dashboardArticles() {

        List<DashboardArticle> result = DashboardManager.ALL_CART_ARTICLES
                .stream().map(new Function<CartArticle, DashboardArticle>() {
                    @Override
                    public DashboardArticle apply(CartArticle cartArticle) {
                        DashboardArticle result = new DashboardArticle(false, cartArticle, DashboardManager.getArticleById(cartArticle.getArticle()));
                        return result;
                    }
                })
                .collect(Collectors.toList());
        return result;
    }
}

