package com.shop.Controller;

import com.shop.Until.SaveFileUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {


        @GetMapping("/upload")
        public String showUploadForm(Model model) {


            return "UploadImage";
        }
    private static final Path root_avt = Paths.get("src/uploads/UserAvatar");
    private static final Path Product_Image = Paths.get("src/uploads/ProductImage");
        @PostMapping("/upload")
        public String handleUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes , Model model) {

            //            // Xử lý tệp tin đã upload
//            // Lưu tệp tin vào thư mục /path/to/uploads/
            try {
                SaveFileUntil.save(file,root_avt);
                System.out.println("savesuccess");
                String filepathigm =file.getOriginalFilename();
                model.addAttribute("name_image", filepathigm);
                return "UploadImage";
            } catch (Exception e) {
                model.addAttribute("message", "Lỗi upload file!");
                return "UploadImage";
            }

//            return "redirect:/upload";
        }


}
