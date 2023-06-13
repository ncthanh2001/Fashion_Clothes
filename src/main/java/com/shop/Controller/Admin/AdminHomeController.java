package com.shop.Controller.Admin;

import com.shop.Constant.PageSize;
import com.shop.Constant.PathUpload;
import com.shop.Entity.Account;
import com.shop.Entity.Category;
import com.shop.Service.AccountService;
import com.shop.Until.SaveFileUntil;
import com.shop.Until.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountService accountService;

    @GetMapping("/home")
    public String getHome(Model model , RedirectAttributes redirectAttributes){
       Account account = accountService.findByUsername(sessionService.get("username"));
       model.addAttribute("account",account);
       if(!account.getAdmin()){
           redirectAttributes.addFlashAttribute("infor","bạn không có quyền truy cập vào đường dẫn này ");
           return "redirect:/login";
       }
        return "views/Admin/index_Admin";
    }

    @GetMapping("/account")
    public String getAccount(Model model, @ModelAttribute("accountEdit") Account accountEdit, @RequestParam(defaultValue = "0", value = "page",required = false) int page){
        Account account = accountService.findByUsername(sessionService.get("username"));
        model.addAttribute("flag_account",true);

        Pageable pageable = PageRequest.of(page, PageSize.PAGE_SIZE);
        Page<Account> accountPage = accountService.findByActivated(true,pageable);
        List<Account> accountList = accountPage.getContent();
        int totalPage = accountPage.getSize();

        model.addAttribute("accountList",accountList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",totalPage);

        model.addAttribute("account",account);
        model.addAttribute("accountEdit",accountEdit);
        return "views/Admin/pages/forms/general";
    }


    @GetMapping("/account/edit")
    public String GetEditAccount(Model model, @RequestParam(defaultValue = "0", value = "page",required = false) int page,@RequestParam("username") String username){
        Account account = accountService.findByUsername(sessionService.get("username"));
        model.addAttribute("account",account);
        model.addAttribute("flag_account",true);
        Pageable pageable = PageRequest.of(page, PageSize.PAGE_SIZE);
        Page<Account> accountPage = accountService.findByActivated(true,pageable);
        List<Account> accountList = accountPage.getContent();
        int totalPage = accountPage.getSize();

        model.addAttribute("accountList",accountList);

        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",totalPage);
        Account accountEdit = accountService.findByUsername(username);
        if(accountEdit != null){
            model.addAttribute("accountEdit",accountEdit);
            return "views/Admin/pages/forms/general";
        }

        return "views/Admin/pages/forms/general";
    }

    @PostMapping("/account/edit")
    public String PostEditAccount(Model model , @ModelAttribute("accountEdit") Account accountEdit
            , @RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(defaultValue = "0", value = "page",required = false) int page ){

        Pageable pageable = PageRequest.of(page, PageSize.PAGE_SIZE);
        Page<Account> accountPage = accountService.findAll(pageable);
        List<Account> accountList = accountPage.getContent();
        int totalPage = accountPage.getSize();

        model.addAttribute("accountList",accountList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",totalPage);


        try {
            if(file !=null){
                SaveFileUntil.save(file, PathUpload.PATH_AVATAR_IMAGE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Account accountcurrent = accountService.findByUsername(accountEdit.getUsername());
        if(accountEdit!=null && accountEdit.getUsername().equals(accountcurrent.getUsername())){
            accountcurrent.setFullname(accountEdit.getFullname());
            accountcurrent.setEmail(accountEdit.getEmail());
            if(file.isEmpty()){
                accountcurrent.setPhoto(file.getOriginalFilename());
            }
            accountService.save(accountcurrent);
            model.addAttribute("success","Cập nhật thành công");
            return "views/Admin/pages/forms/general";
        }else{
            model.addAttribute("error" , "Cập nhật thất bại");
            return "views/Admin/pages/forms/general";
        }
    }
    @PostMapping("/account/delete")
    @ResponseBody
    public String PostdeleteAccount(Model model , @RequestParam("username" )String username
            , @RequestParam(defaultValue = "0", value = "page",required = false) int page
    ){
        System.out.println("delete post");
        Account accountcurrent = accountService.findByUsername(username);
        if(accountcurrent != null){
            accountService.delete(accountcurrent,false);
            model.addAttribute("success" , "Xóa Thành Công ");
            return "Success";
        }else{
            model.addAttribute("error" , "Xóa Thất Bại ");
            return "Error";
        }

    }



}
