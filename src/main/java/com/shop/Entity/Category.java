package com.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.context.annotation.Bean;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

//    @OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//    private List<Product> listProduct;
//    private Set<Product> products = new LinkedHashSet<>();

}