package com.shop.Service.Impl;

import com.shop.Entity.DiscountedProduct;
import com.shop.Entity.Product;
import com.shop.Repository.DiscountedProductsRepository;
import com.shop.Service.DiscountedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountedProductServiceImpl implements DiscountedProductService {
    @Autowired
    DiscountedProductsRepository discountedProductsRepository;

    @Override
    public List<DiscountedProduct> findAll() {
        return discountedProductsRepository.findAll();
    }

    @Override
    public Page<DiscountedProduct> findAll(Pageable pageable) {
        return discountedProductsRepository.findAll(pageable);
    }

    @Override
    public DiscountedProduct findById(int Id) {
        return discountedProductsRepository.findById(Id);
    }


}
