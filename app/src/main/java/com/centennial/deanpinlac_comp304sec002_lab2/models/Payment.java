package com.centennial.deanpinlac_comp304sec002_lab2.models;

import com.centennial.deanpinlac_comp304sec002_lab2.enums.PaymentMethod;

public class Payment {
    private PaymentMethod paymentMethod;
    private Card card;
    private String total;

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
