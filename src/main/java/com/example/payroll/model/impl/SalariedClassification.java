package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;

import java.time.LocalDate;

public class SalariedClassification implements PaymentClassification {
    private final double salary;

    public SalariedClassification(final double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public double calculatePay(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        return salary;
    }
}
