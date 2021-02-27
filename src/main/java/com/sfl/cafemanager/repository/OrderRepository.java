package com.sfl.cafemanager.repository;

import com.sfl.cafemanager.entity.OrderEntity;
import com.sfl.cafemanager.entity.TableEntity;
import com.sfl.cafemanager.entity.UserEntity;
import com.sfl.cafemanager.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrdersByTableAndStatus(TableEntity tableEntity, OrderStatus status);

    List<OrderEntity> findByWaiter(UserEntity userEntity);
}
