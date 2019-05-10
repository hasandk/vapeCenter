package com.vapecenter.demo.service;

import com.vapecenter.demo.models.AboutUs;
import com.vapecenter.demo.models.Cart;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CustomerService {
    Users getUser(int id);
    ArrayList<Products> getProducts();

    Products getProductById(int productId);

    AboutUs getAboutInfo(int aboutUsId);

    Products addProduct(Products product);

    int countPages(ArrayList<Products> products);
}
