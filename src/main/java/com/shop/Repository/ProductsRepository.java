package com.shop.Repository;

import com.shop.Entity.Category;
import com.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Product findByName(String name);
    Product findById(int Id);
    List<Product>findByPriceBetween(BigDecimal a , BigDecimal b );
    List<Product>findTopByPrice(BigDecimal price);
    Page<Product>findAll(Pageable page);
    Page<Product> findByIsDelete(Boolean delete,Pageable pageable);
    Page<Product>findByCategory(Category category, Pageable pageable);


}