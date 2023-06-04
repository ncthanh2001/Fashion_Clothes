package com.shop.Controller;

import com.shop.Entity.Account;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/product")
    public String getProduct(Model model){
        model.addAttribute("account",new Account());
        return "views/User/product-details";
    }
}
