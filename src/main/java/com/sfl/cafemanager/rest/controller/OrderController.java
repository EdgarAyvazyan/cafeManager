package com.sfl.cafemanager.rest.controller;

import com.sfl.cafemanager.configuration.security.jwt.domain.UserPrincipal;
import com.sfl.cafemanager.enums.OrderStatus;
import com.sfl.cafemanager.enums.ProductInOrderStatus;
import com.sfl.cafemanager.rest.model.Order;
import com.sfl.cafemanager.rest.model.OrderRequest;
import com.sfl.cafemanager.rest.model.ProductInOrder;
import com.sfl.cafemanager.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @return All orders from DB.
     */
    @GetMapping
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_MANAGER)")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * @param order     to persist in database
     * @param principal to check if this order is users order.
     * @return Created order with id
     */
    @PostMapping
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_WAITER)")
    public Order createOrder(@AuthenticationPrincipal UserPrincipal principal, @RequestBody OrderRequest order) {
        return orderService.createOrder(principal.getId(), order);
    }

    /**
     * @param order     to persist in database
     * @param principal to check if this order is users order.
     * @return Created order with id
     */
    @PostMapping("/products")
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_WAITER)")
    public Order addProductToOrder(@AuthenticationPrincipal UserPrincipal principal, @RequestBody OrderRequest order) {
        return orderService.addProductsToOrder(principal.getId(), order);
    }

    /**
     * @param orderId   which must be edited
     * @param status    new status for edit.
     * @param principal to check if this order is users order.
     * @return updated order object
     */
    @PatchMapping("/{orderId}")
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_WAITER)")
    public Order editOrderStatus(@AuthenticationPrincipal UserPrincipal principal,
                                 @PathVariable Long orderId, @RequestParam OrderStatus status) {
        return orderService.editOrderStatus(principal.getId(), orderId, status);
    }

    /**
     * @param productRowId which must be updated
     * @param principal    to check if this order is users order.
     * @param status       new status for edit.
     * @param count        updated count.
     * @return updated productsInOrder object.
     */
    @PatchMapping("/products/{productRowId}")
    @PreAuthorize("hasRole(T(com.sfl.cafemanager.enums.Role).ROLE_WAITER)")
    public ProductInOrder editProductsInOrder(@AuthenticationPrincipal UserPrincipal principal,
                                              @PathVariable UUID productRowId,
                                              @RequestParam(required = false) ProductInOrderStatus status,
                                              @RequestParam(required = false) Integer count) {
        return orderService.editProductsInOrder(principal.getId(), productRowId, status, count);
    }
}
