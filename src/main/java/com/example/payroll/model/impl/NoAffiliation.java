package com.example.payroll.model.impl;

import com.example.payroll.model.Affiliation;

import java.time.LocalDate;

public class NoAffiliation implements Affiliation {
    public double calculateDeductions(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        return 0;
    }
}
