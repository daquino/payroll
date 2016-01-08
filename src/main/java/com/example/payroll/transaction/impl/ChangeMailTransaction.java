package com.example.payroll.transaction.impl;

import com.example.payroll.model.MailMethod;
import com.example.payroll.model.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private final String address;

    public ChangeMailTransaction(final int empId, final String address) {
        super(empId);
        this.address = address;
    }

    @Override
    protected PaymentMethod getPaymentMethod() {
        return new MailMethod(address);
    }
}
