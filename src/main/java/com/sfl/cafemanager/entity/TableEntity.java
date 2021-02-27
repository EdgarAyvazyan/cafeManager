package com.sfl.cafemanager.entity;

import com.sfl.cafemanager.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cafe_table")
public class TableEntity  extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private UserEntity waiter;

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> orders;

    public TableEntity() {
    }

    public TableEntity(Long id, UserEntity waiter, List<OrderEntity> orders) {
        this.id = id;
        this.waiter = waiter;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getWaiter() {
        return waiter;
    }

    public void setWaiter(UserEntity waiter) {
        this.waiter = waiter;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
