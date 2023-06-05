package com.shop.Service;

import com.shop.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategorieService {
    List<Category>findAll();
    Optional<Category> findById(int id);
}
