package com.shop.Entity;

import jakarta.persistence.Access;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer Id;
    private String name;
    private BigDecimal Price ;
    private Integer qty;
    private String img;
    private BigDecimal total;

    public void calculateTotal() {
        this.total = Price.multiply(BigDecimal.valueOf(qty));
    }

    public void setPrice(BigDecimal price) {
        Price = price;
        calculateTotal();
    }

    public void setQty(Integer qty) {
        this.qty = qty;
        calculateTotal();
    }

    public CartItem(Integer id, String name, BigDecimal price, Integer qty, String img) {
        Id = id;
        this.name = name;
        Price = price;
        this.qty = qty;
        this.img = img;
        calculateTotal();
    }
}
