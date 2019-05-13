package com.vapecenter.demo.service;

import com.vapecenter.demo.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CustomerService {
    Users getUser(int id);
    ArrayList<Products> getProducts();

    ArrayList<Products> getProductsByCategory(int categoryId);

    ArrayList<Products> searchProduct(String search);

    Products getProductById(int productId);

    Category getCategoryById(int categoryId);

    List<ShipingMethod> getShippingMethods();
    AboutUs getAboutInfo(int aboutUsId);

    Products addProduct(Products product);

    boolean createOrder(List<Cart> cart, Checkout checkout, ShipingMethod shipingMethod, double totalPrice);
}
