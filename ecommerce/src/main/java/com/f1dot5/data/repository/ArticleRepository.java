package com.f1dot5.data.repository;

import com.f1dot5.data.Article;

import java.util.Optional;

public interface ArticleRepository {

    Iterable<Article> findAllAvailable();

    Article findById(Long id);
}
