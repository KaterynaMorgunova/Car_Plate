package com.f1dot5.data.repository;

import com.f1dot5.data.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Article> findAllAvailable() {
        return jdbcTemplate.query(
                "select id, created_at, name, description, image_url, price, currency, available_quantity from Article where available_quantity > 0",
                this::mapRowToArticle);
    }

    @Override
    public Article findById(Long id) {
        List<Article> results = jdbcTemplate.query(
                "select id, created_at, name, description, image_url, price, currency, available_quantity from Article from Customer where id=?",
                this::mapRowToArticle,
                id);
        return results.size() == 0 ? null : results.get(0);
    }

    private Article mapRowToArticle(ResultSet row, int rowNum)
            throws SQLException {
        return new Article(
                row.getLong("id"),
                new java.util.Date(row.getDate("created_at").getTime()),
                row.getString("name"),
                row.getString("description"),
                row.getString("image_url"),
                row.getFloat("price"),
                row.getString("currency"),
                row.getInt("available_quantity")
        );
    }
}
