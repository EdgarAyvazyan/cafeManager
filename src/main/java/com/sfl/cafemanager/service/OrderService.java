package com.sfl.cafemanager.service;


import com.sfl.cafemanager.enums.OrderStatus;
import com.sfl.cafemanager.enums.ProductInOrderStatus;
import com.sfl.cafemanager.rest.model.Order;
import com.sfl.cafemanager.rest.model.OrderRequest;
import com.sfl.cafemanager.rest.model.ProductInOrder;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Long waiterId, OrderRequest order);

    List<Order> getAllOrders();

    Order editOrderStatus(Long waiterId, Long orderId, OrderStatus status);

    ProductInOrder editProductsInOrder(Long waiterId, UUID productsInOrderId, ProductInOrderStatus status, Integer count);

    Order addProductsToOrder(Long id, OrderRequest order);
}
