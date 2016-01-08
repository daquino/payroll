package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentSchedule;

import java.time.LocalDateTime;

public class MonthlySchedule implements PaymentSchedule {
    public boolean isPayDate(final LocalDateTime date) {
        return false;
    }
}
