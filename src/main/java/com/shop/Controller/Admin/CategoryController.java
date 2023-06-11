package com.shop.Controller.Admin;

import com.shop.Constant.PageSize;
import com.shop.Entity.Account;
import com.shop.Entity.Category;
import com.shop.Service.AccountService;
import com.shop.Service.CategorieService;
import com.shop.Until.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountService accountService;
    @Autowired
    CategorieService categorieService;

    @GetMapping("/category")
    public String category(Model model , @ModelAttribute("category") Category category
            , @RequestParam(defaultValue = "0", value = "page",required = false) int page){
        model.addAttribute("flag_category",true);
        Account account = accountService.findByUsername(sessionService.get("username"));
        model.addAttribute("account",account);

        Pageable  pageable= PageRequest.of(page, PageSize.PAGE_SIZE);
        Page<Category> categoryPage = categorieService.findByIsDelete(false , pageable);
        List<Category> categoryList = categoryPage.getContent();
         int totalPage = categoryPage.getSize();
        for (Category cate:categoryList
             ) {
            System.out.println("naem:"+cate.getName());
        }
        model.addAttribute("categoryList",categoryList);

//        model.addAttribute("category",category);

        return "/views/Admin/pages/forms/category";
    }
}
