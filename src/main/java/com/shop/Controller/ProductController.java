package com.shop.Controller;

import com.shop.Entity.Account;
import com.shop.Entity.Product;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/product/id={id}")
    public String getProduct(Model model , @PathVariable("id")int id) {
        model.addAttribute("account", new Account());
        Pageable pageable = PageRequest.of(1,4);
        Page<Product> productList = productService.findAll(pageable);
        model.addAttribute("list_footer",productList.getContent());
            model.addAttribute("product",productService.findById(id));
        return  "views/User/product-details";
    }
//@GetMapping("/product") suw dung url   th:href="@{/home/product(id=${discount.getProduct().getId()})}"
//public String getProduct(Model model , @RequestParam("id")int id){
//    model.addAttribute("account",new Account());
//    Product product = productService.findById(id);
//    if(product!= null){
//        model.addAttribute("product",product);
//    }
//    return "views/User/product-details";
//}
}
