package com.sfl.cafemanager.repository;

import com.sfl.cafemanager.entity.ProductInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrderEntity, UUID> {
}
