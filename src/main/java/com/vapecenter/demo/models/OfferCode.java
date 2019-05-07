package com.vapecenter.demo.models;

public class OfferCode {
    int offerCodsId, discountAmount;
    String code;
    Boolean active;

    public OfferCode() {
    }

    public int getOfferCodsId() {
        return offerCodsId;
    }

    public void setOfferCodsId(int offerCodsId) {
        this.offerCodsId = offerCodsId;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "OfferCode{" +
                "offerCodsId=" + offerCodsId +
                ", discountAmount=" + discountAmount +
                ", code='" + code + '\'' +
                ", active=" + active +
                '}';
    }
}
