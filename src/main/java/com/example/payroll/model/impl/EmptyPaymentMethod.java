package com.example.payroll.model.impl;

import com.example.payroll.model.Paycheck;
import com.example.payroll.model.PaymentMethod;

public class EmptyPaymentMethod implements PaymentMethod {
    public void pay(final Paycheck paycheck) {

    }
}
