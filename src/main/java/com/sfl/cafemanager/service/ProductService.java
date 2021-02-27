package com.sfl.cafemanager.service;


import com.sfl.cafemanager.rest.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(Product product);
}
