package com.shop.Service;

import com.shop.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategorieService {
    List<Category>findAll();

    Page<Category> findByIsDelete(boolean active , Pageable pageable);

    Category findById(int id);
}
