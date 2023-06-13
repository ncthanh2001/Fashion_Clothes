package com.shop.Service.Impl;

import com.shop.Entity.Category;
import com.shop.Entity.Product;
import com.shop.Repository.ProductsRepository;
import com.shop.Service.CategorieService;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategorieService categorieService;
    @Override
    public Page<Product> findAll( Pageable page) {
        return productsRepository.findAll(page);
    }

    @Override
    public Page<Product> findByIsDelete(Boolean delete , Pageable pageable) {
        return productsRepository.findByIsDelete(delete,pageable);
    }

    @Override
    public Product save(Product product) {

        return productsRepository.save(product);
    }

    @Override
    public Page<Product> findByCategory(int cateId,Pageable pageable) {
      Category category=  categorieService.findById(cateId);
        return productsRepository.findByCategory(category,pageable);
    }

    @Override
    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product delete(Product product) {
        product.setIsDelete(true);
        return productsRepository.save(product);
    }


    @Override
    public Product findByName(String name) {
        return productsRepository.findByName(name);
    }

    @Override
    public Product findById(int id) {
        return productsRepository.findById(id);
    }


    @Override
    public List<Product> findByPriceBetween(BigDecimal a, BigDecimal b) {
        return productsRepository.findByPriceBetween(a,b);
    }

    @Override
    public List<Product> findTopByPrice(BigDecimal price) {
        return productsRepository.findTopByPrice(price);
    }



}
