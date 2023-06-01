package com.shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@Table(name = "OrderDetails")
public class OrderDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
//    @Basic
//    @Column(name = "order_id", nullable = true,insertable=false, updatable=false)
//    private Integer orderId;
//    @Basic
//    @Column(name = "product_id", nullable = true,insertable=false, updatable=false)
//    private Integer productId;
    @Basic
    @Column(name = "Price", nullable = true, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "Quantity", nullable = true)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name= "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Orders orders;

    @ManyToOne
    @JoinColumn(name= "product_id" , referencedColumnName = "Id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Products products;
   }
