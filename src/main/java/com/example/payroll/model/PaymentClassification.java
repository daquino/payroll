package com.example.payroll.model;

import java.time.LocalDate;

public interface PaymentClassification {
    double calculatePay(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate);

}
