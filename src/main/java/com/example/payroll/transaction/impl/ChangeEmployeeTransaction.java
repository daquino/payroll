package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final int empId;

    public ChangeEmployeeTransaction(final int empId) {
        this.empId = empId;
    }


    public void execute() {
        Employee employee = PayrollDatabase.getEmployee(empId);
        if(employee != Employee.EMPTY) {
            change(employee);
        }
    }

    protected abstract void change(final Employee employee);
}
