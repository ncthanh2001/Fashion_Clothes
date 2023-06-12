package com.shop.Controller.Admin;

import com.shop.Constant.PageSize;
import com.shop.Constant.PathUpload;
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

        Pageable pageable = PageRequest.of(1, PageSize.PAGE_SIZE);
        Page<Product> productPage = productService.findAll(pageable);
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
          productService.save(product);
          redirectAttributes.addAttribute("succes","upade thành công");
      }
      else{
          try{
              SaveFileUntil.save(file, PathUpload.PATH_PRODUCT_IMAGE);
              product.setImage(file.getOriginalFilename());
              productService.save(product);
              redirectAttributes.addAttribute("succes","upade thành công");
          }catch (Exception e ){
              e.printStackTrace();
          }
          System.out.println("file k chống");
      }
    return "redirect:/admin/product";
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



