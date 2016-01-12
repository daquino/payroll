package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;

import java.time.LocalDate;

public class EmptyPaymentClassification implements PaymentClassification {
    public double calculatePay(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        return 0;
    }
}
