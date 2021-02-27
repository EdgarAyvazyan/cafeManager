package com.sfl.cafemanager.rest.model;

import com.sfl.cafemanager.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    private Long id;
    private Table table;
    private List<ProductInOrder> productInOrders;
    private OrderStatus status;
}
