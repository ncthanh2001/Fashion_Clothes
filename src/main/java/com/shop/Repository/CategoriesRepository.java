package com.shop.Repository;

import com.shop.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    Page<Category> findAll(Specification<Category> spec, Pageable pageable);

    Page<Category> findByIsDelete(boolean active , Pageable pageable);

    Category findById(int id);
}