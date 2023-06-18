package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();
    private String name;
    private String description;
    private String imageUrl;
    private float price;
    private String currency = "USD";
    private int availableQuantity = 1;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
