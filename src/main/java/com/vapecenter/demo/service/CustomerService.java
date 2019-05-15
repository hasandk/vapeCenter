package com.vapecenter.demo.service;

import com.vapecenter.demo.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CustomerService {
    ArrayList<Category> getAllCategories();

    Users getUser(int id);
    ArrayList<Products> getProducts();

    ArrayList<Products> getProductsByCategory(int categoryId);

    ArrayList<Products> searchProduct(String search);

    Products getProductById(int productId);

    ShipingMethod getShippingMethodById(int shippingId);

    Category getCategoryById(int categoryId);

    List<ShipingMethod> getShippingMethods();
    AboutUs getAboutInfo(int aboutUsId);

    Products addProduct(Products product);

    boolean createOrder(List<Cart> cart, Checkout checkout, ShipingMethod shipingMethod, double totalPrice);
    int countPages(ArrayList<Products> products);
    int modulus(ArrayList<Products> products);
    ArrayList<Integer> getPageArray(int pages);
    ArrayList<Products> list15(ArrayList<Products> productList, int page);
}
