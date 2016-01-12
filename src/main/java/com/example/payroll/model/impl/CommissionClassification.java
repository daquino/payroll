package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.SalesReceipt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public SalesReceipt getSalesReceipt(final LocalDateTime salesDate) {
        SalesReceipt receipt = null;
        for (SalesReceipt salesReceipt : salesReceipts) {
            if (salesReceipt.getSalesDate().equals(salesDate.truncatedTo(ChronoUnit.MINUTES))) {
                receipt = salesReceipt;
                break;
            }
        }
        return receipt;
    }

    public void addSalesReceipt(final SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }

    public double calculatePay(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        BigDecimal biweeklyPay = new BigDecimal(salary).divide(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal commission = calculateCommissionTotal(payPeriodStartDate, payPeriodEndDate);
        return biweeklyPay.add(commission).doubleValue();
    }

    private BigDecimal calculateCommissionTotal(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        BigDecimal commission = BigDecimal.ZERO;
        for (SalesReceipt salesReceipt : salesReceipts) {
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
