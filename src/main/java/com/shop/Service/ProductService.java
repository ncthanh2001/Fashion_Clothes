package com.shop.Service;

import com.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product findByName(String name);
    Product findById(int id);
    List<Product>findByPriceBetween(BigDecimal a , BigDecimal b );
    List<Product>findTopByPrice(BigDecimal price);
    Page<Product>findAll(Pageable page);
    Page<Product> findByIsDelete(Boolean delete ,Pageable pageable);
    Product save(Product product);
    Page<Product>findByCategory(int cateId,Pageable pageable);
    List<Product> findAll();

    Product delete(Product product);
}
