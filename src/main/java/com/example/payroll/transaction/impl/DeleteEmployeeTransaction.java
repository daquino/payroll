package com.example.payroll.transaction.impl;

import com.example.payroll.transaction.Transaction;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {
    private final int empId;
    public DeleteEmployeeTransaction(final int empId) {
        this.empId = empId;
    }

    public void execute() {
        GlobalPayrollDatabase.deleteEmployee(empId);
    }
}
