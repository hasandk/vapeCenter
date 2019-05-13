package com.vapecenter.demo.service;

import com.vapecenter.demo.models.*;
import com.vapecenter.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    JdbcTemplate template;

    Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Override
    public Users getUser(int id) {
        return customerRepo.getUser(id);
    }

    @Override
    public ArrayList<Products> getProducts() {
        return customerRepo.getProducts();
    }

    @Override
    public ArrayList<Products> getProductsByCategory(int categoryId){
        return customerRepo.getProductsByCategory(categoryId);
    }

    @Override
    public ArrayList<Products> searchProduct(String search){
        ArrayList<Products> products = customerRepo.searchProduct(search);
        log.info("service searchProduct result: " + products.size());
        return products;
    }

    @Override
    public Products getProductById(int productId) {
        return customerRepo.getProductById(productId);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return customerRepo.getCategoryById(categoryId);
    }

    @Override
    public List<ShipingMethod> getShippingMethods() {
        return customerRepo.getShippingMethods();
    }

    @Override
    public AboutUs getAboutInfo(int aboutUsId) {
        return customerRepo.getAboutInfo(aboutUsId);
    }

    @Override
    public Products addProduct(Products product) {
        return customerRepo.addProduct(product);
    }

    @Override
    public boolean createOrder(List<Cart> cart, Checkout checkout, ShipingMethod shipingMethod, double totalPrice){
        return customerRepo.createOrder(cart, checkout, shipingMethod, totalPrice);
    }
}
