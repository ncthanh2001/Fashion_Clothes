package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "DiscountedProducts")
@Data
public class DiscountedProducts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
//    @Basic
//    @Column(name = "product_id", nullable = false,insertable=false, updatable=false)
//    private int productId;
    @Basic
    @Column(name = "DiscountedPrice", nullable = true, precision = 2)
    private BigDecimal discountedPrice;
    @Basic
    @Column(name = "DiscountPercentage", nullable = true, precision = 2)
    private BigDecimal discountPercentage;

    @Basic
    @Column(name = "startDate", nullable = true)
    private Date startDate;
    @Basic
    @Column(name = "endDate", nullable = true)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Products product;
   }
