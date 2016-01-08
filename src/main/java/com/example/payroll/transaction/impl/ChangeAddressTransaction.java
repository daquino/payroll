package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private final String address;

    public ChangeAddressTransaction(final int empId, final String address) {
        super(empId);
        this.address = address;
    }

    @Override
    protected void change(final Employee employee) {
        employee.setAddress(address);
    }
}
