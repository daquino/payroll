package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.SalesReceipt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommissionClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;
    private Map<LocalDateTime, SalesReceipt> salesReceipts;

    public CommissionClassification(final double salary, final double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
        this.salesReceipts = new HashMap<LocalDateTime, SalesReceipt>();
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public SalesReceipt getSalesReceipt(final LocalDateTime salesDate) {
        return salesReceipts.getOrDefault(salesDate, SalesReceipt.EMPTY);
    }

    public void addSalesReceipt(final LocalDateTime salesDate, final double salesAmount) {
        salesReceipts.put(salesDate, new SalesReceipt(salesDate, salesAmount));
    }

    public double calculatePay(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        BigDecimal biweeklyPay = new BigDecimal(salary).divide(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal commission = calculateCommissionTotal(payPeriodStartDate, payPeriodEndDate);
        return biweeklyPay.add(commission).doubleValue();
    }

    private BigDecimal calculateCommissionTotal(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        BigDecimal commission = BigDecimal.ZERO;
        for (SalesReceipt salesReceipt : salesReceipts.values()) {
            if (isWithinPeriod(payPeriodStartDate, payPeriodEndDate, salesReceipt.getSalesDate())) {
                commission = commission.add(new BigDecimal(salesReceipt.getAmount())
                        .multiply(new BigDecimal(commissionRate))
                        .setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }
        return commission;
    }

    private boolean isWithinPeriod(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate, final LocalDateTime salesDate) {
        LocalDate localSalesDate = salesDate.toLocalDate();
        return (localSalesDate.isAfter(payPeriodStartDate) || localSalesDate.isEqual(payPeriodStartDate)) &&
                (localSalesDate.isBefore(payPeriodEndDate) || localSalesDate.isEqual(payPeriodEndDate));
    }
}
