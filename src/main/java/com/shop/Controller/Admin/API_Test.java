package com.shop.Controller.Admin;

import com.shop.Service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class API_Test {
    @Autowired
    CategorieService categorieService;
    @GetMapping("/getall")
    @ResponseBody
    ResponseEntity<?> getallCategory(){
        return ResponseEntity.ok( categorieService.findAll());
    }
}
