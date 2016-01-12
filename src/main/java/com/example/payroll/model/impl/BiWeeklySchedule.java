package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

public class BiWeeklySchedule implements PaymentSchedule {
    public boolean isPayDate(final LocalDate date) {
        return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) % 2 == 0 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    public LocalDate getPayPeriodStartDate(final LocalDate payDate) {
        return payDate.minusDays(14);
    }
}
