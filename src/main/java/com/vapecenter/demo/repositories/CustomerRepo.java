package com.vapecenter.demo.repositories;

import com.vapecenter.demo.models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepo {
    Users getUser(int id);

    ArrayList<Products> getProducts();

    ArrayList<Products> getAllProducts();

    ArrayList<Products> getProductsByCategory(int categoryId);

    ArrayList<Products> searchProduct(String search);

    Products getProductById(int productId);

    Category getCategoryById(int categoryId);

    ShipingMethod getShippingMethodById(int shippingId);

    List<ShipingMethod> getShippingMethods();
    AboutUs getAboutInfo(int aboutUsId);

    Products addProduct(Products product);

    boolean createOrder(List<Cart> cart, Checkout checkout, ShipingMethod shipingMethod, double totalPrice);

    ArrayList<Category> getAllCategories();

    void updateStock(Integer productId, Integer stock);
}
