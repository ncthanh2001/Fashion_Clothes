package com.shop.Controller;

import com.shop.Entity.*;
import com.shop.Service.*;
import com.shop.Until.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class ShopCartController {
    @Autowired
    Cart_Item_Service cartItem;
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/cart")
    public String GetCart(Model model){
        model.addAttribute("list_cart",cartItem);

        return "/views/User/shop-cart";
    }
    @GetMapping ("/cart/add")
    public  String PostCart(Model model, @RequestParam(value = "id" ,required = false) int id){
        cartItem.add(id);
        return "redirect:/home/cart";
    }

    @GetMapping("/cart/remove")
    public String removeCart(Model model, @RequestParam(value = "id",required = false) int id){
        cartItem.remove(id);
        for (CartItem cartItem1 : cartItem.getItems()){
            System.out.println(cartItem1.getName());
        }
        return "redirect:/home/cart";
    }

    @GetMapping("/cart/sub")
    public String subCart(Model model, @RequestParam(value = "id",required = false) int id){
        cartItem.sub(id);
        return "redirect:/home/cart";
    }

    @GetMapping("/cart/checkout")
    public String getCheckOut(Model model){
        Account account_login = accountService.findByUsername(sessionService.get("username"));
        if(account_login!= null){
            model.addAttribute("account_login",account_login);
        }

        model.addAttribute("listCartItem",cartItem);
        return "/views/User/checkout";
    }

    @PostMapping ("/cart/checkout")
    public String postCheckOut(Model model, @RequestParam(value = "address" , required = false) String address
    ,@RequestParam(value = "phone",required = false) String phone){
        Account account_login = accountService.findByUsername(sessionService.get("username"));
        Order orderCreate = new Order();
        orderCreate.setUsername(account_login);
        orderCreate.setAddress(address);
        orderService.save(orderCreate);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(orderCreate);
        for (CartItem cart: cartItem.getItems()
             ) {
            Product productTemp = productService.findById(cart.getId());
            orderDetail.setProduct(productTemp);
            orderDetail.setPrice(cart.getPrice());
            orderDetail.setQuantity(cart.getQty());
        }
        orderDetailService.save(orderDetail);
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(orderCreate.getId());
        BigDecimal totalPriceDetail = new BigDecimal(0);
        for (OrderDetail orderDetail1: orderDetailList
        ) {
            totalPriceDetail=totalPriceDetail.add(orderDetail1.getPrice());
        }
        model.addAttribute("total" , totalPriceDetail);
        model.addAttribute("orderdetailList",orderDetailList);
        model.addAttribute("phone",phone);
        model.addAttribute("success","thành công");
        model.addAttribute("order",orderCreate);
        model.addAttribute("order_detail",orderDetail);
        return "/views/User/checkout_success";
    }


}
