package com.sfl.cafemanager.entity;

import com.sfl.cafemanager.entity.base.BaseEntity;
import com.sfl.cafemanager.enums.ProductInOrderStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product_in_order")
public class ProductInOrderEntity  extends BaseEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatus status;

    public ProductInOrderEntity() {
    }

    public ProductInOrderEntity(UUID id, ProductEntity product, Integer count, ProductInOrderStatus status) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ProductInOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ProductInOrderStatus status) {
        this.status = status;
    }
}
