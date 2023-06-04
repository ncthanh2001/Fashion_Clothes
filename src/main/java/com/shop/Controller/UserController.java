package com.shop.Controller;

import com.shop.Entity.Accounts;
import com.shop.Service.AccountService;
import com.shop.Until.CookieService;
import com.shop.Until.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    AccountService accountService;

    @Autowired
    CookieService cookieService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String getLogin(Model model){
        String username = cookieService.getValue("username");
        if(username != null){
            model.addAttribute("username",username);
            model.addAttribute("password",cookieService.getValue("password"));
            model.addAttribute("remember",Boolean.parseBoolean(cookieService.getValue("remember")));
        }
        return "/views/login";
    }
    @PostMapping("login")
    public String postLogin(Model model,
                            @RequestParam("username") String username ,
                            @RequestParam("password") String password,
                            @RequestParam(value = "remember",required = false) Boolean remember){
        Accounts accounts = accountService.findByUsernameAndPassword(username,password);
        if(accounts != null){
            if(remember != null && remember){
                cookieService.add("username",accounts.getUsername(),1);
                cookieService.add("password",accounts.getPassword(),1);
                cookieService.add("remember", Boolean.toString(remember),1);
                model.addAttribute("successMessage","Login Thành công !");
                sessionService.set("username",accounts.getUsername());
                sessionService.set("role",accounts.isAdmin());
                return  "views/login";
            }else {
                cookieService.remove("username");
                cookieService.remove("password");
                cookieService.remove("remember");
                sessionService.set("username",accounts.getUsername());
                sessionService.set("role",accounts.isAdmin());
                model.addAttribute("successMessage","Login Thành công !");
                return  "views/login";
            }
        }else{
            model.addAttribute("username",username);
            model.addAttribute("password",password);
            model.addAttribute("errorMessage","Login Thất bại ");
            return  "views/login";
        }
    }

    @GetMapping("/register")
    public String getRegister(@ModelAttribute("account") Accounts accounts,Model model){
        model.addAttribute("account",new Accounts());
        return "views/register";
    }

    @PostMapping("/register")
    public  String postRegister(@ModelAttribute("account") Accounts accounts,Model model,
                                @RequestParam(value = "file",required = false) MultipartFile file,
                                @RequestParam("repeatPass" )String repeatPass){
        List<Accounts> list_check = accountService.findAll();
        if(accounts!= null && accounts.getPassword().equals(repeatPass)){
            for (Accounts account_check : list_check) {
                if(account_check.getUsername().equals(accounts.getUsername())){
                    model.addAttribute("inform", "username đã tồn tại ");
                    return "views/register";
                } else if (account_check.getEmail().equals(accounts.getEmail())) {
                    model.addAttribute("inform","email đã được sử dụng ");
                    return "views/register";
                }else{
                    accounts.setPhoto(file.getOriginalFilename());
                    accountService.save(accounts);
                    model.addAttribute("account",new Accounts());
                    model.addAttribute("successMessage","Đăng Ký Tài Khoản Thành công");
                    sessionService.set("username",accounts.getUsername());
                    sessionService.set("role",accounts.isAdmin());
                    return "views/register";
                }
            }

        }
        model.addAttribute("errorMessage","Đăng Ký Tài Khoản Thất Bại , Kiểm tra lại thông tin và mật khẩu");
        return "views/register";
    }

    @GetMapping("/edit_profile")
    public String ChangeProfile(){
        return "redirect:/home";
    }

    @GetMapping("/log_out")
    public String logOut(){
        sessionService.remove("username");
        sessionService.remove("role");
        return "redirect:/login";
    }

}
