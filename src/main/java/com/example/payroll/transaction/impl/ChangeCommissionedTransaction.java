package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.BiWeeklySchedule;
import com.example.payroll.model.impl.CommissionClassification;
import com.example.payroll.transaction.Transaction;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private final double salary;
    private final double commissionRate;
    public ChangeCommissionedTransaction(final int empId, final double salary, final double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionClassification(salary, commissionRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BiWeeklySchedule();
    }
}
