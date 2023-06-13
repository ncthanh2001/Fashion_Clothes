package com.shop.Controller;

import com.shop.Constant.Infor;
import com.shop.Constant.PathUpload;
import com.shop.Entity.Account;
import com.shop.Service.AccountService;
import com.shop.Service.Impl.EmailService;
import com.shop.Until.CookieService;
import com.shop.Until.SaveFileUntil;
import com.shop.Until.SessionService;
import io.swagger.models.auth.In;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    AccountService accountService;

    @Autowired
    CookieService cookieService;

    @Autowired
    SessionService sessionService;

    @Autowired
    EmailService emailService;

    @GetMapping("/login")
    public String getLogin(Model model){
        String username = cookieService.getValue("username");
        if(username != null){
            model.addAttribute("username",username);
            model.addAttribute("password",cookieService.getValue("password"));
            model.addAttribute("remember",Boolean.parseBoolean(cookieService.getValue("remember")));
        }
        String infor = (String) model.asMap().get("infor");
        model.addAttribute("infor",infor);
        return "/views/login";
    }
    @PostMapping("login")
    public String postLogin(Model model,
                            @RequestParam("username") String username ,
                            @RequestParam("password") String password,
                            @RequestParam(value = "remember",required = false) Boolean remember){
        Account accounts = accountService.findByUsernameAndPassword(username,password);
        if(accounts != null){
            if(remember != null && remember){
                cookieService.add("username",accounts.getUsername(),1);
                cookieService.add("password",accounts.getPassword(),1);
                cookieService.add("remember", Boolean.toString(remember),1);
                model.addAttribute("successMessage","Login Thành công !");
                sessionService.set("username",accounts.getUsername());
                sessionService.set("role",accounts.getAdmin());
                sessionService.set("photo",accounts.getPhoto());
                return  "views/login";
            }else {
                cookieService.remove("username");
                cookieService.remove("password");
                cookieService.remove("remember");
                sessionService.set("username",accounts.getUsername());
                sessionService.set("role",accounts.getAdmin());
                sessionService.set("photo",accounts.getPhoto());
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
    public String getRegister(@ModelAttribute("account") Account accounts,Model model){
        model.addAttribute("account",new Account());
        return "views/register";
    }

    @PostMapping("/register")
    public  String postRegister(@ModelAttribute("account") Account accounts,Model model,
                                @RequestParam(value = "file",required = false) MultipartFile file,
                                @RequestParam("repeatPass" )String repeatPass) throws MessagingException {
        List<Account> list_check = accountService.findAll();
        if(accounts!= null && accounts.getPassword().equals(repeatPass)){
            for (Account account_check : list_check) {
                if(account_check.getUsername().equals(accounts.getUsername())){
                    model.addAttribute("inform", "username đã tồn tại ");
                    return "views/register";
                } else if (account_check.getEmail().equals(accounts.getEmail())) {
                    model.addAttribute("inform","email đã được sử dụng ");
                    return "views/register";
                }else{
                    try {
                        SaveFileUntil.save(file, PathUpload.PATH_AVATAR_IMAGE);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    accounts.setPhoto(file.getOriginalFilename());
                    accountService.save(accounts);
                    model.addAttribute("account",new Account());
                    emailService.sendEmail(accounts.getEmail(), Infor.REGISTER.getTittle(), Infor.REGISTER.getContent(),null);
                    model.addAttribute("successMessage","Đăng Ký Tài Khoản Thành công");
                    sessionService.set("username",accounts.getUsername());
                    sessionService.set("role",accounts.getAdmin());
                    sessionService.set("photo",accounts.getPhoto());
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
    @PostMapping("/edit_profile")
    public String EditProfile(@ModelAttribute("Account")Account accounts,
                              @RequestParam(value = "file",required = false) MultipartFile file
    , RedirectAttributes redirectAttributes){
        Account currentAccount = accountService.findByUsername(accounts.getUsername());
        if(currentAccount != null){
            currentAccount.setFullname(accounts.getFullname());
            currentAccount.setEmail(accounts.getEmail());
            currentAccount.setPhoto(file.getOriginalFilename());
            try {
                SaveFileUntil.save(file,PathUpload.PATH_AVATAR_IMAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
            accountService.save(currentAccount);
            redirectAttributes.addFlashAttribute("success","update Thành công");
            return "redirect:/home";
        }
         return "redirect:/home";
    }

    @GetMapping("/forgot-pass")
    public String forGotPass(Model model ){
        return "/views/forgotPass";
    }
    @PostMapping("/forgot-pass")
    public String Postforgotpass(Model model,@RequestParam("username")String username ,
                                 @RequestParam("email")String email) throws MessagingException {
        Optional<Account> account=   accountService.findByUsernameAndEmail(username,email);
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            code.append(randomNumber);
        }
        if(account.isPresent()){{
            account.get().setPassword(String.valueOf(code));
            accountService.save(account.get());
            emailService.sendEmail(account.get().getEmail(),Infor.FORGOTPASS.getTittle(), Infor.FORGOTPASS.getContent()+code, null);
            model.addAttribute("success","Gửi mail thành công vui lòng check mail ");
        }}else{
            model.addAttribute("error","Không tìm thấy username hoặc email");
        }
        return "/views/forgotPass";
    }
}
