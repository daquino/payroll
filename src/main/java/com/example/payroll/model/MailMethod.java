package com.example.payroll.model;

public class MailMethod implements PaymentMethod {
    private final String address;

    public MailMethod(final String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void pay(final Paycheck paycheck) {

    }
}
