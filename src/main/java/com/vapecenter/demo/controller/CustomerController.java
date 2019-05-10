package com.vapecenter.demo.controller;

import com.vapecenter.demo.models.AboutUs;
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

        /*productsList.add(new Products(1, "Supervape", 5000000));
        productsList.add(new Products(2, "Crapvape", 5));
        productsList.add(new Products(3, "Bluevape", 550));
        productsList.add(new Products(4, "Ecovape", 320));
        productsList.add(new Products(5, "DSBvape", 1500));*/

    }

    @Autowired
    CustomerService customerService;

    private final String CART = "cart";

    //List<Products> productsList = new ArrayList<>();

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

        List<Products> productsList = customerService.getProducts();

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
    public String deleteCart(@RequestParam("productId")Integer productId, Model model) throws Exception {
        log.info("deleteCart is called...");

        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getProductId() == productId) {
                cartList.remove(i);
            }
        }

        return "redirect:/cart";
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

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        AboutUs aboutUs = customerService.getAboutInfo(1);

        model.addAttribute("aboutUs", aboutUs);

        return "aboutUs";
    }
}
