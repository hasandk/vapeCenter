package com.vapecenter.demo.controller;

import com.vapecenter.demo.models.Cart;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.Users;
import com.vapecenter.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CustomerController {

    public CustomerController(){

        cartList.add(new Cart(3,2));
        cartList.add(new Cart(5,6));
        cartList.add(new Cart(4,3));

        productsList.add(new Products(1, "Supervape", 5000000));
        productsList.add(new Products(2, "Crapvape", 5));
        productsList.add(new Products(3, "Bluevape", 550));
        productsList.add(new Products(4, "Ecovape", 320));
        productsList.add(new Products(5, "DSBvape", 1500));

    }

    @Autowired
    CustomerService customerService;

    private final String CART = "cart";

    List<Cart> cartList = new ArrayList<>();
    List<Products> productsList = new ArrayList<>();

    Logger log = Logger.getLogger(CustomerController.class.getName());

    @GetMapping("/")
    public String index(){
        log.info("index called");
        Users user = customerService.getUser(1);
        log.info(""+user.getFirstName()+" "+user.getEmail());

        return "index";
    }


    @GetMapping("/cart")
    public String cart(Model model){
        log.info("Cart is called...");

        double total = 0;

        for(Cart c : cartList){
            for(Products p : productsList){
                if (c.getProductId() == p.getProductId()) {
                    total = total + ( c.getAmount() * p.getPrice() );
                }
            }
        }

        model.addAttribute("carts", cartList);
        model.addAttribute("products", productsList);
        model.addAttribute("total", total);

        return CART;
    }

    @RequestMapping(value = "/editCart", method = RequestMethod.POST)
    public String editCart(@RequestParam("productId")Integer productId, @RequestParam("amount")Integer amount, Model model) throws Exception{
        log.info("editCart is called...");

        for (Cart c : cartList) {
            if(c.getProductId() == productId){
                c.setAmount(amount);
            }
        }

        return "redirect:/cart";
    }

    @RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
    public String deleteCart(@RequestParam("productId")Integer productId, Model model) throws Exception{
        log.info("deleteCart is called...");

        for(int i = 0; i < cartList.size(); i++){
            if(cartList.get(i).getProductId() == productId){
                cartList.remove(i);
            }
        }

        return "redirect:/cart";
    }
}
