package com.f1dot5.dashboard;

import com.f1dot5.data.Article;
import com.f1dot5.data.CartArticle;
import com.f1dot5.data.repository.ArticleRepository;
import com.f1dot5.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DashboardManager {
    private final ArticleRepository articleRepo;

    @Autowired
    public DashboardManager(
            ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<DashboardArticle> dashboardArticles() {

        return StreamSupport.stream(articleRepo.findAllAvailable().spliterator(), false)

                .map(new Function<Article, DashboardArticle>() {
                    @Override
                    public DashboardArticle apply(Article article) {
                        DashboardArticle result = new DashboardArticle(
                                false,
                                new CartArticle(article.getId(), 1, null, new Date(), null),
                                article
                        );
                        return result;
                    }
                })
                .collect(Collectors.toList());
    }
}

