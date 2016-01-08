package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.BiWeeklySchedule;
import com.example.payroll.model.impl.CommissionClassification;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double salary;
    private final double commissionRate;
    public AddCommissionedEmployee(final int empId, final String name, final String address, final double salary,
                                   final double commissionRate) {
        super(empId, name, address);
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
