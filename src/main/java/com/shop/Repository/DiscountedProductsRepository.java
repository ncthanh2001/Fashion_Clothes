package com.shop.Repository;

import com.shop.Entity.DiscountedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountedProductsRepository extends JpaRepository<DiscountedProducts, Integer>, JpaSpecificationExecutor<DiscountedProducts> {

}