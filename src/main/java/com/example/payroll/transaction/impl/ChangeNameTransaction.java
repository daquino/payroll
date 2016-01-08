package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private final String name;

    public ChangeNameTransaction(final int empId, final String name) {
        super(empId);
        this.name = name;
    }

    @Override
    protected void change(final Employee employee) {
        employee.setName(name);
    }
}
