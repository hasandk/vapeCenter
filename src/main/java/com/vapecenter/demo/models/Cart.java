package com.vapecenter.demo.models;

import java.util.ArrayList;

public class Cart {
    private int cartId, productId, amount;
    private double totalPrice;
    //private ArrayList<Products> productList;

    public Cart() {
    }

    public Cart(int productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /*public ArrayList<Products> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Products> productList) {
        this.productList = productList;
    }*/

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", productList="  +
                '}';
    }
}
