package com.f1dot5.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CartOrder {
    private Long id;
    private Date createdAt = new Date();
    private float total;
    private String currency;
    //private Long customerDelivery;

    private List<CartArticle> cartArticles = new ArrayList<>();
}
