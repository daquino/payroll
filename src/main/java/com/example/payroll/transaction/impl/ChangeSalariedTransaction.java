package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.MonthlySchedule;
import com.example.payroll.model.impl.SalariedClassification;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private final double salary;
    public ChangeSalariedTransaction(final int empId, final double salary) {
        super(empId);
        this.salary = salary;

    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
