package com.shop.Repository;

import com.shop.Entity.DiscountedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountedProductsRepository extends JpaRepository<DiscountedProduct, Integer>, JpaSpecificationExecutor<DiscountedProduct> {
// Page<DiscountedProducts> findAll(Pageable pageable);




}