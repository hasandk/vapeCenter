package com.vapecenter.demo.controller;

import com.vapecenter.demo.models.Users;
import com.vapecenter.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class CustomerController {

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
    public String cart(){

        return CART;
    }
}
