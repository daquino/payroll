package com.example.payroll.model;

import java.time.LocalDate;

public interface Affiliation {
    double calculateDeductions(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate);
}
