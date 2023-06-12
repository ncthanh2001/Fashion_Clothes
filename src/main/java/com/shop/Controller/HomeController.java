package com.shop.Controller;

import com.shop.Constant.PageSize;
import com.shop.Entity.Account;
import com.shop.Entity.Category;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("flag_home",true);

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

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(defaultValue = "0", value = "page",required = false) int page){
       Pageable pageable = PageRequest.of(page,PageSize.PAGE_SIZE );
       Page<Product> productPage = productService.findAll(pageable);
       List<Product>productList = productPage.getContent();
       int totalPage = productPage.getSize();

        Map<Integer , String> categoryMap = new HashMap<>();
        List<Category> categoryList= categorieService.findAll();
        for (Category catrgorytemp :categoryList) {
            categoryMap.put(catrgorytemp.getId(),catrgorytemp.getName());
        }


        model.addAttribute("category",categoryMap);
       model.addAttribute("currentPage",page);
       model.addAttribute("productList",productList);
       model.addAttribute("totalPages",totalPage);
        model.addAttribute("flag_shop",true);
    return "views/User/shop";
    }

    @GetMapping("/shop/category={id}")
    public String findCategori(Model model, @PathVariable("id")int id
    ,@RequestParam(defaultValue = "0", value = "page",required = false) int page){
        Pageable pageable = PageRequest.of(page,PageSize.PAGE_SIZE);
        Page<Product> productPage = productService.findByCategory(id,pageable);
        List<Product> productList = productPage.getContent();
        int totalPage = productPage.getTotalPages();
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPage);
        model.addAttribute("productList",productList);

        Map<Integer , String> categoryMap = new HashMap<>();
        List<Category> categoryList= categorieService.findAll();
        for (Category catrgorytemp :categoryList) {
            categoryMap.put(catrgorytemp.getId(),catrgorytemp.getName());
        }
        model.addAttribute("category",categoryMap);
        model.addAttribute("flag_shop",true);
        return "views/User/shop";
    }
}
