package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentMethod;
import com.example.payroll.model.impl.HoldMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    private final String address;

    public ChangeHoldTransaction(final int empId, final String address) {
        super(empId);
        this.address = address;
    }

    @Override
    protected PaymentMethod getPaymentMethod() {
        return new HoldMethod(address);
    }
}
