package com.vapecenter.demo.models;

public class Checkout {
    String name, email, street, city;
    int phone, zipcode;

    public Checkout() {
    }

    public Checkout(String name, String email, String street, String city, int phone, int zipcode) {
        this.name = name;
        this.email = email;
        this.street = street;
        this.city = city;
        this.phone = phone;
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "checkout{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", phone=" + phone +
                ", zipcode=" + zipcode +
                '}';
    }
}
