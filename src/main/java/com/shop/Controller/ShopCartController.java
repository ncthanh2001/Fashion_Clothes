package com.shop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class ShopCartController {
    @GetMapping("/cart")
    public String GetCart(){
        return "/views/User/shop-cart";
    }
}
