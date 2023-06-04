//package com.shop.Entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.*;
//import jakarta.persistence.Table;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//@Data
//@Entity
//@Table(name="DiscountedProducts")
//@Accessors(chain = true)
//public class DiscountedProducts {
//    @Id
//    @Column(name = "Id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer Id;
//    @Column(name = "DiscountPercentage")
//    private BigDecimal DiscountPercentage;
//    @Column(name ="DiscountedPrice")
//    private BigDecimal DiscountedPrice;
//    @Column(name="startDate")
//    private LocalDate startDate;
//@Column(name="endDate")
//    private LocalDate endDate;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Products product;
//}
