package com.onlineshop.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.onlineshop.creation.ProductBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product implements ProductInterface {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category")
    private String category;

    @Column(name = "PriceBeforeDiscount")
    private BigDecimal priceBeforeDiscount;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "EndPrice")
    private BigDecimal endPrice;

    @Column(name = "IsInEditor")
    private boolean isInEditor;

    public Product() {

    }

    public Product(ProductBuilder builder) {
        this.name = builder.getName();
        this.category = builder.getCategory();
        this.priceBeforeDiscount = builder.getPriceBeforeDiscount();
        this.discount = builder.getDiscount();
    }
}
