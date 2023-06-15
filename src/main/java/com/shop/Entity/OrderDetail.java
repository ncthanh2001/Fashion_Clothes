package com.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE )
    @JoinColumn(name = "order_id" , referencedColumnName = "Id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE )
    @JoinColumn(name = "product_id" , referencedColumnName = "Id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Product product;

    @Column(name = "Price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "Quantity")
    private Integer quantity;

}