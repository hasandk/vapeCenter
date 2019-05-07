package com.vapecenter.demo.models;

public class Orders {
    int  orderId, userId, shippingId, offerCodesId;
    double totalPrice;

    public Orders() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getOfferCodesId() {
        return offerCodesId;
    }

    public void setOfferCodesId(int offerCodesId) {
        this.offerCodesId = offerCodesId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", shippingId=" + shippingId +
                ", offerCodesId=" + offerCodesId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
