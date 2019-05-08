package com.vapecenter.demo.service;

import com.vapecenter.demo.models.Cart;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.Users;
import com.vapecenter.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Products getProductById(int productId) {
        return customerRepo.getProductById(productId);
    }
}
