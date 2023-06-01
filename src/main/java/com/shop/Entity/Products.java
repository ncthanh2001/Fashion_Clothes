package com.shop.Entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Products")
@Data
public class Products {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "Image", nullable = true, length = 2147483647)
    private String image;
    @Basic
    @Column(name = "Price", nullable = false, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Basic
    @Column(name = "Quantity", nullable = false)
    private int quantity;
//    @Basic
//    @Column(name = "category_id", nullable = false,insertable=false, updatable=false)
//    private int categoryId;
    @Basic
    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;
    @Basic
    @Column(name = "Size", nullable = true,insertable=false, updatable=false)
    private String Size;
    @Basic
    @Column(name = "Color", nullable = true,insertable=false, updatable=false)
    private String Color;

    @OneToMany(mappedBy = "products")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Categories categories;

    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<DiscountedProducts> DiscountedProducts;


}
