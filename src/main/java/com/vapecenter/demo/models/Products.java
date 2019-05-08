package com.vapecenter.demo.models;

public class Products {
    int productId, stock;
    String name, description, pictureLink;
    Boolean active;
    double price;

    public Products() {
    }

    public Products(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", active=" + active +
                ", price=" + price +
                '}';
    }
}
