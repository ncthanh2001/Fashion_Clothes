package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Lob
    @Column(name = "Image")
    private String image;

    @Column(name = "Price", nullable = false, precision = 18)
    private BigDecimal price;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    @Nationalized
    @Column(name = "\"Size\"", length = 10)
    private String size;

    @Nationalized
    @Column(name = "Color", length = 20)
    private String color;

    @OneToMany(mappedBy = "product")
    private Set<DiscountedProduct> discountedProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}