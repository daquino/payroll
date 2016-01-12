package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.TimeCard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class HourlyClassification implements PaymentClassification {
    private final double hourlyRate;
    private Map<LocalDate, TimeCard> timeCards;

    public HourlyClassification(final double hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.timeCards = new HashMap<LocalDate, TimeCard>();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public TimeCard getTimeCard(final LocalDate date) {
        return timeCards.getOrDefault(date, TimeCard.EMPTY);
    }

    public void addTimeCard(final TimeCard timeCard) {
        timeCards.put(timeCard.getDate(), timeCard);
    }

    public double calculatePay(final LocalDate payPeriodStartDate, final LocalDate payPeriodEndDate) {
        double hours = 0;
        double overtimeHours = 0;
        for (TimeCard timeCard : getTimeCardsWithinPayPeriod(payPeriodStartDate, payPeriodEndDate)) {
            if (timeCard.getHours() > 8) {
                hours += 8;
                overtimeHours = timeCard.getHours() - 8;
            }
            else {
                hours += timeCard.getHours();
            }
        }
        return new BigDecimal(calculateHourlyTotal(hours) + calculateOvertimeTotal(overtimeHours))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private Collection<TimeCard> getTimeCardsWithinPayPeriod(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        LocalDate date = LocalDate.of(payPeriodStartDate.getYear(), payPeriodStartDate.getMonth(), payPeriodStartDate.getDayOfMonth());
        List<TimeCard> timeCardsWithinPeriod = new ArrayList<TimeCard>();
        while (date.isBefore(payPeriodEndDate) || date.isEqual(payPeriodEndDate)) {
            TimeCard timeCard = timeCards.get(date);
            if (timeCard != null) {
                timeCardsWithinPeriod.add(timeCards.getOrDefault(date, TimeCard.EMPTY));
            }
            date = date.plusDays(1);
        }
        return timeCardsWithinPeriod;
    }

    private double calculateHourlyTotal(final double hours) {
        return hours * hourlyRate;
    }

    private double calculateOvertimeTotal(final double overtimeHours) {
        return overtimeHours * (hourlyRate * 1.5);
    }
}
