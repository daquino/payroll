package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.transaction.Transaction;

public class DeleteEmployeeTransaction implements Transaction {
    private final int empId;
    public DeleteEmployeeTransaction(final int empId) {
        this.empId = empId;
    }

    public void execute() {
        PayrollDatabase.deleteEmployee(empId);
    }
}
