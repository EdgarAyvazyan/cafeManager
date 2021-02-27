package com.sfl.cafemanager.mapper;

import com.sfl.cafemanager.entity.ProductEntity;
import com.sfl.cafemanager.rest.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toDomain(ProductEntity productEntity);

    ProductEntity toEntity(Product product);
}
