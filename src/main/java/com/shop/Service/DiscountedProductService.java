package com.shop.Service;

import com.shop.Entity.DiscountedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountedProductService {
    List<DiscountedProduct> findAll();
    Page<DiscountedProduct> findAll(Pageable pageable);

}
