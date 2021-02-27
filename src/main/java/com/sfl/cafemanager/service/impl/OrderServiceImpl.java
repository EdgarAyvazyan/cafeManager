package com.sfl.cafemanager.service.impl;

import com.sfl.cafemanager.entity.*;
import com.sfl.cafemanager.enums.OrderStatus;
import com.sfl.cafemanager.enums.ProductInOrderStatus;
import com.sfl.cafemanager.service.exception.ExistingOrderException;
import com.sfl.cafemanager.service.exception.ForbiddenException;
import com.sfl.cafemanager.mapper.OrderMapper;
import com.sfl.cafemanager.mapper.ProductInOrderMapper;
import com.sfl.cafemanager.repository.*;
import com.sfl.cafemanager.rest.model.Order;
import com.sfl.cafemanager.rest.model.OrderRequest;
import com.sfl.cafemanager.rest.model.ProductInOrder;
import com.sfl.cafemanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductInOrderRepository productsInOrderRepository;

    private final ProductRepository productRepository;

    private final TableRepository tableRepository;

    private final OrderMapper orderMapper;

    private final ProductInOrderMapper productInOrderMapper;

    @Override
    @Transactional
    public Order createOrder(Long waiterId, OrderRequest order) {
        TableEntity tableEntity = tableRepository.findById(order.getTableId()).orElseThrow(IllegalArgumentException::new);
        UserEntity waiterEntity = userRepository.findById(waiterId).orElseThrow(IllegalArgumentException::new);

        if (tableEntity.getWaiter() == null || !waiterId.equals(tableEntity.getWaiter().getId())) {
            throw new ForbiddenException();
        }

        List<OrderEntity> openOrders = orderRepository.findOrdersByTableAndStatus(tableEntity, OrderStatus.OPEN);

        if (!openOrders.isEmpty()) {
            final String err = String.format("Table %d has open orders", order.getTableId());
            log.error(err);
            throw new ExistingOrderException();
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setWaiter(waiterEntity);
        orderEntity.setStatus(OrderStatus.OPEN);
        orderEntity.setTable(tableEntity);

        List<ProductInOrderEntity> products = toEntityList(order.getProductsInOrder());
        orderEntity.setProductInOrders(products);

        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.toDomain(savedOrderEntity);
    }

    @Override
    public Order addProductsToOrder(Long waiterId, OrderRequest order) {
        TableEntity tableEntity = tableRepository.findById(order.getTableId()).orElseThrow(IllegalArgumentException::new);
        List<OrderEntity> openOrders = orderRepository.findOrdersByTableAndStatus(tableEntity, OrderStatus.OPEN);

        if (openOrders.isEmpty()) {
            final String err = String.format("Table %d has no open orders", order.getTableId());
            log.error(err);
            throw new RuntimeException(err);
        }

        OrderEntity orderEntity = openOrders.get(0);
        if (!waiterId.equals(orderEntity.getTable().getWaiter().getId())) {
            throw new ForbiddenException();
        }


        List<ProductInOrderEntity> products = toEntityList(order.getProductsInOrder());
        orderEntity.getProductInOrders().addAll(products);

        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.toDomain(savedOrderEntity);
    }

    @Override
    public Order editOrderStatus(Long waiterId, Long orderId, OrderStatus status) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        if (!waiterId.equals(orderEntity.getTable().getWaiter().getId())) {
            throw new ForbiddenException();
        }

        orderEntity.setStatus(status);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.toDomain(savedOrderEntity);
    }

    @Override
    public ProductInOrder editProductsInOrder(Long waiterId, UUID productsInOrderId, ProductInOrderStatus status, Integer count) {
        ProductInOrderEntity productInOrderEntity = productsInOrderRepository.findById(productsInOrderId).orElseThrow(IllegalArgumentException::new);

        if (count != null) {
            productInOrderEntity.setCount(count);
        }

        if (status != null) {
            productInOrderEntity.setStatus(status);
        }

        ProductInOrderEntity savedEntity = productsInOrderRepository.save(productInOrderEntity);
        return productInOrderMapper.toDomain(savedEntity);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }

    private List<ProductInOrderEntity> toEntityList(List<ProductInOrder> productsInOrder) {
        return productsInOrder.stream()
                .map(productInOrder -> {
                    ProductInOrderEntity productInOrderEntity = productInOrderMapper.toEntity(productInOrder);
                    ProductEntity productEntity = productRepository.findById(productInOrder.getProductId()).orElseThrow(IllegalArgumentException::new);
                    productInOrderEntity.setProduct(productEntity);
                    return productInOrderEntity;
                })
                .collect(Collectors.toList());
    }
}
