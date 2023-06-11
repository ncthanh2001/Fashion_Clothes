package com.shop.Controller;

import com.shop.Entity.Order;
import com.shop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class test {
    @Autowired
    AccountService accountService;
    @Autowired
    CategorieService categorieService;
    @Autowired
    DiscountedProductService discountedProductService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProductService productService;
    @GetMapping("/test")
    public String Test(){
        List<Order> orderList = orderService.findAll();
        for (Order oder: orderList
             ) {
            System.out.println(oder);
        }
        return "test";
    }
}
