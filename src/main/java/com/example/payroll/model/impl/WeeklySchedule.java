package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {
    public boolean isPayDate(final LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

    public LocalDate getPayPeriodStartDate(final LocalDate payDate) {
        return payDate.minusDays(5);
    }
}
