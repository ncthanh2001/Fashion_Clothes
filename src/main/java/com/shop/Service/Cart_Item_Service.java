package com.shop.Service;

import com.shop.Entity.CartItem;
import com.shop.Entity.Product;

import java.math.BigDecimal;
import java.util.Collection;

public interface Cart_Item_Service {
    CartItem add(int id_product);

    void remove(Integer id);


    CartItem sub(int id_product);

    Collection<CartItem> getItems();

    BigDecimal totalPrice();


}
