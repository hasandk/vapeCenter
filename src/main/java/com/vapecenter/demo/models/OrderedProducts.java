package com.vapecenter.demo.models;

public class OrderedProducts {
    int orderedProductsId, productId,orderId,amount;
    String productName;
    double price;

    public OrderedProducts() {
    }

    public int getOrderedProductsId() {
        return orderedProductsId;
    }

    public void setOrderedProductsId(int orderedProductsId) {
        this.orderedProductsId = orderedProductsId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderedProducts{" +
                "orderedProductsId=" + orderedProductsId +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
