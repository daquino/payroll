package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.HourlyClassification;
import com.example.payroll.model.impl.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double hourlyRate;

    public ChangeHourlyTransaction(final int empId, final double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
