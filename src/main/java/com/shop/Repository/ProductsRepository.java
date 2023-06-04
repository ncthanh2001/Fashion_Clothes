package com.shop.Repository;

import com.shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
//    @Query(value = "select e from Product e where e.size = :size1  ")
    Product findByName(String name);

}