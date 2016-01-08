package com.example.payroll.model;

import java.time.LocalDate;

public class ServiceCharge {
    public static final ServiceCharge EMPTY = new ServiceCharge(null, 0);
    private final LocalDate chargeDate;
    private final double amount;

    public ServiceCharge(final LocalDate chargeDate, final double amount) {
        this.chargeDate = chargeDate;
        this.amount = amount;
    }

    public LocalDate getChargeDate() {
        return chargeDate;
    }

    public double getAmount() {
        return amount;
    }
}
