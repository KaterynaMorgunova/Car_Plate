package com.f1dot5.data.repository;

import com.f1dot5.data.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query("SELECT o FROM Article o WHERE o.availableQuantity > 0")
    List<Article> readArticleAvailable();
}
