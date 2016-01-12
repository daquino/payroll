package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlySchedule implements PaymentSchedule {
    public boolean isPayDate(final LocalDate date) {
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());
        return date.equals(endOfMonth);
    }

    public LocalDate getPayPeriodStartDate(final LocalDate payDate) {
        return LocalDate.of(payDate.getYear(), payDate.getMonth(), 1);
    }
}
