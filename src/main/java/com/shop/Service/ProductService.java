package com.shop.Service;

import com.shop.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findByName(String name);
}
