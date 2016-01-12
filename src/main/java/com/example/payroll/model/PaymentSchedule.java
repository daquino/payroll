package com.example.payroll.model;

import java.time.LocalDate;

public interface PaymentSchedule {
    boolean isPayDate(LocalDate date);
    LocalDate getPayPeriodStartDate(LocalDate payDate);
}
