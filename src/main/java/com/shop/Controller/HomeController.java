package com.shop.Controller;

import com.shop.Entity.Account;
import com.shop.Entity.DiscountedProduct;
import com.shop.Entity.Product;
import com.shop.Service.*;
import com.shop.Until.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    AccountService accountService;

    @Autowired
    DiscountedProductService discountedProductService;

    @Autowired
    ProductService productService;

    @Autowired
    CategorieService categorieService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    SessionService sessionService;
//    @GetMapping("/test")
//    public String hoem(){
//        Pageable pageable1 = PageRequest.of(0, 10);
////        Page<Product> listtest = productService.findByCategoryAfter(pageable1);
//        List<Product> listtest = productService.findAll();
//        System.out.println("sn phảm a: ");
//        for (Product pro : listtest){
//            System.out.println(pro.toString());
//        }
//        return "views/User/blog";
//    }
    @GetMapping({""})
    public String home(Model model, @RequestParam(defaultValue = "0", value = "page",required = false) int page) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<DiscountedProduct> discountedProductsPage = discountedProductService.findAll(pageable);
        List<DiscountedProduct> discountedProductsList = discountedProductsPage.getContent();
        int totalPage = discountedProductsPage.getTotalPages();

        int pageSize_product = 3;
        Pageable pageable_product = PageRequest.of(page, pageSize_product);
        Page<Product> ProductPage = productService.findAll(pageable_product);
        List<Product> Products_List = ProductPage.getContent();
        int totalPage_Product = ProductPage.getTotalPages();

        model.addAttribute("productList", Products_List);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPage_Product);


   if(discountedProductsList !=null){
       model.addAttribute("discountProduct", discountedProductsList);
       model.addAttribute("currentPage", page);
       model.addAttribute("totalPages", totalPage);
   }
       Account accounts = accountService.findByUsername(sessionService.get("username"));
       if(accounts !=null){
           model.addAttribute("account",accounts);
           System.out.println(accounts.getPhoto());
           return "index";
       }else{
           model.addAttribute("account",new Account());
           return "index";
       }

    }

}
