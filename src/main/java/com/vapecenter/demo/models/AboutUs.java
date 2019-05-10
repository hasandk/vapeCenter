package com.vapecenter.demo.models;

public class AboutUs {
    int aboutUsId, phone, zipcode;
    String description, streetName, pictureLink;

    public AboutUs() {
    }

    public AboutUs(int aboutUsId, String description, int phone, String streetName, int zipcode, String pictureLink) {
        this.aboutUsId = aboutUsId;
        this.phone = phone;
        this.zipcode = zipcode;
        this.description = description;
        this.streetName = streetName;
        this.pictureLink = pictureLink;
    }

    public int getAboutUsId() {
        return aboutUsId;
    }

    public void setAboutUsId(int aboutUsId) {
        this.aboutUsId = aboutUsId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
