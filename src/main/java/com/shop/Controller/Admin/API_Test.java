package com.shop.Controller.Admin;

import com.shop.Entity.Category;
import com.shop.Entity.Product;
import com.shop.Service.CategorieService;
import com.shop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class API_Test {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private ProductService productService;
    @GetMapping("/get-all")
    @ResponseBody
    ResponseEntity<?> getAllCategory(){
       try{
           Category cate = categorieService.findById(2);
           return ResponseEntity.ok(cate);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }
    @GetMapping("/get-product")
    @ResponseBody
    ResponseEntity<?>getProductByid(){
        try{
            Product product = productService.findById(2);
            return  ResponseEntity.ok(product);
        }catch (Exception e){
    e.printStackTrace();
        }
        return null;
    }
}
