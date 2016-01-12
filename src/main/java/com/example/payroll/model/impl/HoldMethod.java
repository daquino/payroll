package com.example.payroll.model.impl;

import com.example.payroll.model.Paycheck;
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

    public void pay(final Paycheck paycheck) {
        paycheck.addField("Disposition", "Hold");
    }
}
