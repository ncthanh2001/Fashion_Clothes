package com.shop.Controller.Admin;

import com.shop.Constant.PageSize;
import com.shop.Constant.PathUpload;
import com.shop.Entity.Account;
import com.shop.Entity.Category;
import com.shop.Entity.Product;
import com.shop.Service.CategorieService;
import com.shop.Service.ProductService;
import com.shop.Until.SaveFileUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategorieService categorieService;
    @GetMapping("/product")
    public String getProductController(Model model
            , @RequestParam(defaultValue = "0", value = "page",required = false) int page
    , @RequestParam(value = "hello" , required = false) String hello
    , @ModelAttribute(value = "product_edit" ,binding = false)Product product_edit){
        model.addAttribute("flag_product",true);
        String success = (String) model.asMap().get("success");
        model.addAttribute("success",success);

        Pageable pageable = PageRequest.of(0, PageSize.PAGE_SIZE);
        Page<Product> productPage = productService.findByIsDelete(false,pageable);
        List<Product> productList = productPage.getContent();
        int totalPage = productPage.getTotalPages();

        model.addAttribute("product_edit",product_edit);
        model.addAttribute("product_list",productList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPage);
        model.addAttribute("hello",hello);

        return  "/views/Admin/pages/forms/product";
    }

    @GetMapping("/product/edit")
        public String GetEditProduct(Model model, RedirectAttributes redirectAttributes
        ,@RequestParam("id") int id){
            redirectAttributes.addAttribute("product_edit",productService.findById(id));
        return "redirect:/admin/product";
        }
    @PostMapping("/product/edit")
    public  String PostEdit(RedirectAttributes redirectAttributes
    ,@ModelAttribute("product_edit")Product product
    ,@RequestParam(value = "file",required = false) MultipartFile file){
      if(file.isEmpty()){
          System.out.println("file tróng");
           product.setImage(productService.findById(product.getId()).getImage());
           product.setCreateDate(LocalDate.now());
          productService.save(product);
          redirectAttributes.addFlashAttribute("success","upade thành công");
      }
      else{
          try{
              SaveFileUntil.save(file, PathUpload.PATH_PRODUCT_IMAGE);
              product.setImage(file.getOriginalFilename());
              product.setCreateDate(LocalDate.now());
              System.out.println("tên file"+file.getOriginalFilename());
              productService.save(product);
              System.out.println("product img "+product.getImage());
              redirectAttributes.addFlashAttribute("success","upade thành công");
          }catch (Exception e ){
              e.printStackTrace();
          }
          System.out.println("file k chống");
      }
    return "redirect:/admin/product";
    }
    @PostMapping("/product/delete")
    @ResponseBody
    public String PostdeleteAccount(Model model , @RequestParam("id" )int id
            , @RequestParam(defaultValue = "0", value = "page",required = false) int page
    ){
        System.out.println("delete_product");
        Product product = productService.findById(id);
        if(product != null){
            productService.delete(product);
            model.addAttribute("success" , "Xóa Thành Công ");
            System.out.println("delete "+product.getIsDelete());
            return "Success";
        }else{
            model.addAttribute("error" , "Xóa Thất Bại ");
            return "Error";
        }

    }



    @ModelAttribute("category_list")
    public Map<Integer,String> Listcategory(){
        Map<Integer,String> map = new HashMap<>();
        List<Category> categoryList = categorieService.findAll();
        for (Category catego: categoryList
             ) {
            map.put(catego.getId(),catego.getName());
        }

        return map;
    }

    }



