package com.shop.Controller.Admin;

import com.shop.Constant.PageSize;
import com.shop.Entity.Order;
import com.shop.Service.OrderDetailService;
import com.shop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/order")
    public String ordercontroller(Model model
    , @RequestParam(defaultValue = "0", value = "page",required = false) int page
    , @ModelAttribute("order_edit")Order order
    ,@RequestParam(value = "success",required = false)String success){
        model.addAttribute("flag_order",true);

        Pageable pageable = PageRequest.of(page, PageSize.PAGE_SIZE);
        Page<Order> orderPage = orderService.findAll(pageable);
        List<Order> orderList = orderPage.getContent();
        int total = orderPage.getTotalPages();

        model.addAttribute("order_edit" , order);
        model.addAttribute("current_page",page);
        model.addAttribute("listOrder", orderList);
        model.addAttribute("totalPage",total);
     model.addAttribute("success",success);
        return "/views/Admin/pages/forms/order";
    }

    @GetMapping("/order/edit")
    public String getOrder(RedirectAttributes redirectAttributes, @RequestParam("id") int Id){
    Optional<Order> order = orderService.findById(Id);
    redirectAttributes.addAttribute("order_edit",order.get());
        return "redirect:/admin/order";
    }

    @PostMapping("/order/edit")
    public String postOrder(RedirectAttributes redirectAttributes , @ModelAttribute("order_edit") Order order){
        System.out.println("id+"+order.getId());
        System.out.println("name"+order.getUsername());
        System.out.println("addreess"+order.getAddress());
        Optional<Order> orderTemp = orderService.findById(order.getId());
        order.setCreateDate(orderTemp.get().getCreateDate());
        orderService.save(order);
        redirectAttributes.addAttribute("success","updatee1 thành công");
        return "redirect:/admin/order";
    }
}
