package com.vapecenter.demo.repositories;

import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.ShipingMethod;
import com.vapecenter.demo.models.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepo {
    Users getUser(int id);

    ArrayList<Products> getProducts();

    Products getProductById(int productId);

    List<ShipingMethod> getShippingMethods();
}
