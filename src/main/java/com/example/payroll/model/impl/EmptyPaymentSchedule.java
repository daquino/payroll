package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentSchedule;

import java.time.LocalDate;

public class EmptyPaymentSchedule implements PaymentSchedule {
    public boolean isPayDate(final LocalDate date) {
        return false;
    }

    public LocalDate getPayPeriodStartDate(final LocalDate payDate) {
        return null;
    }
}
