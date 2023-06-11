package com.shop.Service.Impl;

import com.shop.Entity.CartItem;
import com.shop.Entity.Product;
import com.shop.Service.Cart_Item_Service;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class Cart_Item_Impl implements Cart_Item_Service {
    @Autowired
    ProductService productService;

    private Map<Integer ,CartItem> cartItemMap = new HashMap<>();
    Integer sizeCart = cartItemMap.size();
    @Override
    public CartItem add(int id_product) {
       Product productAdd =  productService.findById(id_product);
       CartItem cartItemAdd = cartItemMap.get(id_product);
       if(cartItemAdd == null){
          cartItemMap.put(id_product,new CartItem(id_product ,productAdd.getName(),productAdd.getPrice(),1 , productAdd.getImage()) ) ;
       }else{
           cartItemAdd.setQty(cartItemAdd.getQty()+1) ;
       }

        return cartItemAdd ;
    }



    @Override
    public void remove(Integer id) {
        cartItemMap.remove(id);
    }

    @Override
    public CartItem sub(int id_product) {
        CartItem cartItemSub = cartItemMap.get(id_product);
        cartItemSub.setQty(cartItemSub.getQty()-1);

        return cartItemSub;
    }

    @Override
    public Collection<CartItem> getItems() {
        return cartItemMap.values();
    }

    @Override
    public BigDecimal totalPrice() {
        BigDecimal total = new BigDecimal(0);
        for(CartItem cartItem : cartItemMap.values()){
           total = total.add(cartItem.getTotal());
        }
        return total;
    }
}
