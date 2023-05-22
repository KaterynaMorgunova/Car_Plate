package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class CartArticle {
    private Long article;
    private int quantity = 1;
//    private Long cartOrder;
    private Date createdAt;
//    private Long customer;
}
