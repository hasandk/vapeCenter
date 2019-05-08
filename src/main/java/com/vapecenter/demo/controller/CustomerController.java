package com.vapecenter.demo.controller;

import com.vapecenter.demo.models.Cart;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.Users;
import com.vapecenter.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.logging.Logger;

@Controller
public class CustomerController {
    private ArrayList<Cart> cartList = new ArrayList<>();

    public CustomerController(){

    }

    @Autowired
    CustomerService customerService;

    private final String CART = "cart";

    Logger log = Logger.getLogger(CustomerController.class.getName());

    @GetMapping("/")
    public String index(){
        log.info("index called");
        Users user = customerService.getUser(1);
        log.info(""+user.getFirstName()+" "+user.getEmail());

        return "index";
    }


    @GetMapping("/cart")
    public String cart() {

        return CART;
    }

    @GetMapping("/listProducts")
    public String listProducts(Model model, Cart cart) {
        log.info("listProducts called...");
        /*ArrayList<Products> test = new ArrayList<>();
        test = customerService.getProducts();
        log.info(test.get(1).getProductId()+"");*/
        model.addAttribute("productList", customerService.getProducts());
        model.addAttribute("cart", cart);

        return "listProducts";
    }

    @PutMapping("/listProducts")
    public String listProducts(@ModelAttribute Cart cart, Model model, Cart cartNew) {
        log.info("listProducts putmapping called...");
        cartList.add(cart);
        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }


        model.addAttribute("productList", customerService.getProducts());
        model.addAttribute("cart", cartNew);
        return "listProducts";
    }

    @GetMapping("viewProduct/{productId}")
    public String viewProduct(@PathVariable("productId") int productId, Model model, Cart cart) {
        log.info("viewProduct called with id="+productId);
        model.addAttribute("product", customerService.getProductById(productId));
        model.addAttribute("cart", cart);

        return "viewProduct";
    }

    @PutMapping("viewProduct/")
    public String viewProduct(@ModelAttribute Cart cart, Model model) {
        log.info("viewProduct putmapping called with id="+cart.getProductId());
        Boolean check = false;

        for(int i = 0;i<cartList.size(); i++) {
            if(cart.getProductId()==cartList.get(i).getProductId()) {
                cartList.get(i).setAmount(cartList.get(i).getAmount()+cart.getAmount());
                check = true;
            }
        }
        if(check == false) {
            cartList.add(cart);
        }

        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }
        model.addAttribute("product",customerService.getProductById(cart.getProductId()));

        return "redirect:/viewProduct/"+cart.getProductId();
    }
}
