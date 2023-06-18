package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity
@Table(name="CustomerOrder")
public class SalesInvoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();
    private float totalPrice;
    private String currency;
    private Long customerDelivery;

    @OneToMany(targetEntity=CartArticle.class, cascade = {CascadeType.ALL})
    private List<CartArticle> cartArticles = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
