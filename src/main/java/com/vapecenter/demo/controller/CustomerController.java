package com.vapecenter.demo.controller;

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

    Logger log = Logger.getLogger(CustomerController.class.getName());

    @GetMapping("/")
    public String index(){
        log.info("index called");

        return "index";
    }
}
