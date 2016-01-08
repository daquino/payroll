package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.SalesReceipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommissionClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;
    private List<SalesReceipt> salesReceipts;

    public CommissionClassification(final double salary, final double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
        this.salesReceipts = new ArrayList<SalesReceipt>();
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public BigDecimal calculatePay(final Date date) {
        return null;
    }

    public SalesReceipt getSalesReceipt(final LocalDateTime salesDate) {
        SalesReceipt receipt = null;
        for(SalesReceipt salesReceipt: salesReceipts) {
            if(salesReceipt.getSalesDate().equals(salesDate.truncatedTo(ChronoUnit.MINUTES))) {
                receipt = salesReceipt;
                break;
            }
        }
        return receipt;
    }

    public void addSalesReceipt(final SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }
}
