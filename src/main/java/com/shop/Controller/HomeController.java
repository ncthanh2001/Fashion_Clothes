package com.shop.Controller;

import com.shop.Entity.Accounts;
import com.shop.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    AccountService accountService;
    @GetMapping({"/"})
    public String home(){
        System.out.println("Home");
        List<Accounts> list_acc = accountService.findAll();
        System.out.println("size " +list_acc.size());
        for (Accounts acc : list_acc
             ) {
            System.out.println(acc.toString());
        }
        return "index";
    }
}
