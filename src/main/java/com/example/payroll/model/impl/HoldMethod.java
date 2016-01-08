package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentMethod;

public class HoldMethod implements PaymentMethod {
    private final String address;

    public HoldMethod() {
        this.address = "";
    }
    public HoldMethod(final String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void sendPayment(final int amount) {

    }
}
