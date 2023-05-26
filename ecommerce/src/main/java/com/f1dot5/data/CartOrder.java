package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class CartOrder {
    private Long id;
    private Date createdAt = new Date();
    private float totalPrice;
    private String currency;
    private Long customerDelivery;

    private List<CartArticle> cartArticles = new ArrayList<>();
}
