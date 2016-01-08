package com.example.payroll.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SalesReceipt {
    private final LocalDateTime salesDate;
    private final double amount;

    public SalesReceipt(final LocalDateTime date, final double amount) {
        this.salesDate = date.truncatedTo(ChronoUnit.MINUTES);
        this.amount = amount;
    }

    public LocalDateTime getSalesDate() {
        return salesDate;
    }

    public double getAmount() {
        return amount;
    }
}
