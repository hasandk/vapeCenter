package com.vapecenter.demo.models;

public class ShipingMethod {
    int shippingId;
    String companyName;
    double price;

    public ShipingMethod() {
    }

    public ShipingMethod(int shippingId, String companyName, double price) {
        this.shippingId = shippingId;
        this.companyName = companyName;
        this.price = price;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShipingMethod{" +
                "shippingId=" + shippingId +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                '}';
    }
}
