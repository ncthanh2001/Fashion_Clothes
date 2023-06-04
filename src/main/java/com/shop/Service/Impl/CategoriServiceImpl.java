package com.shop.Service.Impl;

import com.shop.Entity.Category;
import com.shop.Repository.CategoriesRepository;
import com.shop.Service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriServiceImpl implements CategorieService {
    @Autowired
    CategoriesRepository categoriesRepository;
    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }
}
