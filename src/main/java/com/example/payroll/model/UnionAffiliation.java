package com.example.payroll.model;

import com.example.payroll.util.PaymentDateUtils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private final Map<LocalDate, ServiceCharge> serviceCharges;
    private final double dues;
    private int memberId;

    public UnionAffiliation(final int memberId, final double dues) {
        serviceCharges = new HashMap<LocalDate, ServiceCharge>();
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(final LocalDate chargeDate) {
        return serviceCharges.getOrDefault(chargeDate, ServiceCharge.EMPTY);
    }

    public void addServiceCharge(final ServiceCharge due) {
        serviceCharges.put(due.getChargeDate(), due);
    }

    public double calculateDeductions(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        double totalDues = calculateTotalDues(payPeriodStartDate, payPeriodEndDate);
        double serviceTotal = calculateServiceChargeTotal(payPeriodStartDate, payPeriodEndDate);
        return new BigDecimal(totalDues + serviceTotal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double calculateTotalDues(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        return new BigDecimal(dues * numberOfFridays(payPeriodStartDate, payPeriodEndDate))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private int numberOfFridays(final LocalDate startDate, final LocalDate endDate) {
        LocalDate date = startDate;
        int numberOfFridays = 0;
        while(PaymentDateUtils.isInPayPeriod(startDate, endDate, date)) {
            if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                numberOfFridays++;
            }
            date = date.plusDays(1);
        }
        return numberOfFridays;
    }

    private double calculateServiceChargeTotal(final LocalDate startDate, final LocalDate endDate) {
        LocalDate date = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        double total = 0;
        while(date.isBefore(endDate) || date.isEqual(endDate)) {
            ServiceCharge serviceCharge = serviceCharges.getOrDefault(date, ServiceCharge.EMPTY);
            total += serviceCharge.getAmount();
            date = date.plusDays(1);
        }
        return new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(final int memberId) {
        this.memberId = memberId;
    }
}
