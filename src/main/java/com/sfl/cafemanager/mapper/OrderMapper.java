package com.sfl.cafemanager.mapper;

import com.sfl.cafemanager.entity.OrderEntity;
import com.sfl.cafemanager.entity.ProductInOrderEntity;
import com.sfl.cafemanager.entity.TableEntity;
import com.sfl.cafemanager.rest.model.Order;
import com.sfl.cafemanager.rest.model.ProductInOrder;
import com.sfl.cafemanager.rest.model.Table;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toDomain(OrderEntity orderEntity);

    List<Order> toDomain(List<OrderEntity> orderEntity);

    OrderEntity toEntity(Order order);

    default List<ProductInOrder> productsInOrderEntitiesToDomain(List<ProductInOrderEntity> productInOrderEntities) {
        return productInOrderEntities.stream()
                .map(entity -> {
                    ProductInOrder productInOrder = new ProductInOrder();
                    productInOrder.setId(entity.getId());
                    productInOrder.setCount(entity.getCount());
                    productInOrder.setProductId(entity.getProduct().getId());
                    productInOrder.setStatus(entity.getStatus());
                    return productInOrder;
                })
                .collect(Collectors.toList());
    }

    default Table tableEntityToDomain(TableEntity entity) {
        Table table = new Table();
        table.setId(entity.getId());
        table.setWaiterId(entity.getWaiter().getId());
        return table;
    }
}
