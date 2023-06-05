package com.shop.Repository;

import com.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
//    @Query(value = "select e from Product e where e.size = :size1  ")
    Product findByName(String name);

    Product findById(int Id);

    List<Product> findByColorEquals(String name);

    List<Product>findByPriceBetween(BigDecimal a , BigDecimal b );

    List<Product>findTopByPrice(BigDecimal price);

    Page<Product>findAll(Pageable page);
}