package com.shop.Service.Impl;

import com.shop.Entity.Product;
import com.shop.Repository.ProductsRepository;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;
    @Override
    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product findByName(String name) {
        return productsRepository.findByName(name);
    }
}
