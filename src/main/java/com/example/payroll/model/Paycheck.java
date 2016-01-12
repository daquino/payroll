package com.example.payroll.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Paycheck {
    private LocalDate payPeriodStartDate;
    private LocalDate payPeriodEndDate;
    private double grossPay;
    private Map<String, String> fields;
    private double deductions;
    private double netPay;

    public Paycheck(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
        this.fields = new HashMap<String, String>();
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public LocalDate getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(final double grossPay) {
        this.grossPay = grossPay;
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public void addField(String key, String value) {
        fields.put(key, value);
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(final double deductions) {
        this.deductions = deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(final double netPay) {
        this.netPay = netPay;
    }
}
