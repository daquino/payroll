package com.example.payroll.transaction.impl;

import com.example.payroll.model.DirectMethod;
import com.example.payroll.model.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;
    public ChangeDirectTransaction(final int empId, final String bank, final String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    protected PaymentMethod getPaymentMethod() {
        return new DirectMethod(bank, account);
    }
}
