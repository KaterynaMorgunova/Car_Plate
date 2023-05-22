package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Article {

    private Long id;
    private Date createdAt = new Date();
    private String name;
    private String description;
    private String imageUrl;
    private float price;
    private String currency = "USD";
    private int availableQuantity = 1;
}
