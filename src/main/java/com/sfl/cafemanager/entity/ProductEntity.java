package com.sfl.cafemanager.entity;

import com.sfl.cafemanager.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity  extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private double price;

    public ProductEntity(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
