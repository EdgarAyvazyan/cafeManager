package com.sfl.cafemanager.mapper;

import com.sfl.cafemanager.entity.ProductInOrderEntity;
import com.sfl.cafemanager.rest.model.ProductInOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductInOrderMapper {
    @Mapping(source = "product.id", target = "productId")
    ProductInOrder toDomain(ProductInOrderEntity productInOrderEntity);

    ProductInOrderEntity toEntity(ProductInOrder productInOrder);
}
