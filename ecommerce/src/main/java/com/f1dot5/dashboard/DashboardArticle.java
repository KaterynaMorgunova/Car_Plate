package com.f1dot5.dashboard;

import lombok.Data;

@Data
public class DashboardArticle {
    private final String id;
    private final String name;
    private final String description;
    private final String imageUrl;
    private final float price;
    private final String currency;
    private final int availableQuantity;
    private final int desiredQuantity;
}
