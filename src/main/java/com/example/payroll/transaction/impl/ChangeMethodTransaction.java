package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;
import com.example.payroll.model.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(final int empId) {
        super(empId);
    }

    @Override
    protected void change(final Employee employee) {
        employee.setPaymentMethod(getPaymentMethod());
    }

    protected abstract PaymentMethod getPaymentMethod();
}
