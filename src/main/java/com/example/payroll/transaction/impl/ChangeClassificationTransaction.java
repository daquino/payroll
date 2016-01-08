package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;
import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(final int empId) {
        super(empId);
    }

    @Override
    protected void change(final Employee employee) {
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
    }

    protected abstract PaymentClassification getClassification();
    protected abstract PaymentSchedule getSchedule();
}
