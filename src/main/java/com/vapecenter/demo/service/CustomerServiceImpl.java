package com.vapecenter.demo.service;

import com.vapecenter.demo.models.AboutUs;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.ShipingMethod;
import com.vapecenter.demo.models.Users;
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
    public int countPages(ArrayList<Products> products) {
        int pages = 1;
        int remainder = 0;

        if(products.size()>15) {
            pages = products.size()/15;
            remainder = modulus(products);
            if(remainder !=0) {
                pages++;
            }
        }
        return pages;
    }

    @Override
    public int modulus(ArrayList<Products> products) {
        int modulus = 0;
        modulus = products.size() % 15;

        return modulus;
    }

    @Override
    public ArrayList<Integer> getPageArray(int pages) {
        ArrayList<Integer> pageArray = new ArrayList<>();

        for(int i = 1; i<=pages; i++) {
            pageArray.add(i);
        }

        return pageArray;
    }

    @Override
    public ArrayList<Products> list15(ArrayList<Products> productList, int page) {
        ArrayList<Products> list15 = new ArrayList<>();

        if(modulus(productList) >= 1 && page == countPages(productList)) {
            for(int i = (page * 15) - 15; i < (page*15)-15+modulus(productList); i++) {
                list15.add(productList.get(i));
            }
        }
        else {
            for (int i = (page * 15) - 15; i < page * 15; i++) {
                list15.add(productList.get(i));
            }
        }

        return list15;
    }


}
