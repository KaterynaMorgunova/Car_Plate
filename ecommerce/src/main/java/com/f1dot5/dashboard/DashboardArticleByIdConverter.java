package com.f1dot5.dashboard;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DashboardArticleByIdConverter implements Converter<String, DashboardArticle> {

    public static List<DashboardArticle> ALL_ARTICLES = Arrays.asList(
            new DashboardArticle("1", "Article 1", "Desc 1", "imageUrl 1", 1.0F, "USD", 1, 1),
            new DashboardArticle("2", "Article 2", "Desc 2", "imageUrl 2", 1.0F, "USD", 1, 1),
            new DashboardArticle("3", "Article 3", "Desc 3", "imageUrl 3", 1.0F, "USD", 1, 1),
            new DashboardArticle("4", "Article 4", "Desc 4", "imageUrl 4", 1.0F, "USD", 1, 1),
            new DashboardArticle("5", "Article 5", "Desc 5", "imageUrl 5", 1.0F, "USD", 1, 1),
            new DashboardArticle("6", "Article 6", "Desc 6", "imageUrl 6", 1.0F, "USD", 1, 1),
            new DashboardArticle("7", "Article 7", "Desc 7", "imageUrl 7", 1.0F, "USD", 1, 1),
            new DashboardArticle("8", "Article 8", "Desc 8", "imageUrl 8", 1.0F, "USD", 1, 1),
            new DashboardArticle("9", "Article 9", "Desc 9", "imageUrl 9", 1.0F, "USD", 1, 1),
            new DashboardArticle("10", "Article 10", "Desc 10", "imageUrl 10", 1.0F, "USD", 1, 1),
            new DashboardArticle("11", "Article 11", "Desc 11", "imageUrl 11", 1.0F, "USD", 1, 1)
    );

    @Override
    public DashboardArticle convert(String id) {
        return DashboardArticleByIdConverter.ALL_ARTICLES.stream()
                .filter( x -> x.getId().equals(id))
                .findFirst()
                .get();
    }

}

