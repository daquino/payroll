package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;

import java.math.BigDecimal;
import java.util.Date;

public class SalariedClassification implements PaymentClassification {
    private final double salary;

    public SalariedClassification(final double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public BigDecimal calculatePay(final Date date) {
        return null;
    }
}
