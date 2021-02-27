package com.sfl.cafemanager.entity;

import com.sfl.cafemanager.entity.base.BaseEntity;
import com.sfl.cafemanager.enums.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cafe_order")
public class OrderEntity  extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiter_id")
    private UserEntity waiter;

    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInOrderEntity> productInOrders;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderEntity() {
    }

    public OrderEntity(Long id, TableEntity table, UserEntity waiter, List<ProductInOrderEntity> productInOrders,
                       OrderStatus status) {
        this.id = id;
        this.table = table;
        this.waiter = waiter;
        this.productInOrders = productInOrders;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    public UserEntity getWaiter() {
        return waiter;
    }

    public void setWaiter(UserEntity waiter) {
        this.waiter = waiter;
    }

    public List<ProductInOrderEntity> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrderEntity> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
