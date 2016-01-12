package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;
import com.example.payroll.transaction.Transaction;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final int empId;

    public ChangeEmployeeTransaction(final int empId) {
        this.empId = empId;
    }


    public void execute() {
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        if(employee != Employee.EMPTY) {
            change(employee);
        }
    }

    protected abstract void change(final Employee employee);
}
