package com.f1dot5.order;

import lombok.Data;

@Data
public class Order {
    private final String id;
    private final float total;
    private final String currency;
}
