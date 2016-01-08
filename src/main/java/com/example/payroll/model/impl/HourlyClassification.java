package com.example.payroll.model.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.TimeCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlyClassification implements PaymentClassification {
    private final double hourlyRate;
    private List<TimeCard> timeCards;

    public HourlyClassification(final double hourlyRate) {
        this.hourlyRate = hourlyRate;
        this.timeCards = new ArrayList<TimeCard>();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public TimeCard getTimeCard(final int date) {
        for(TimeCard timeCard : timeCards) {
            if(date == timeCard.getDate()) {
                return timeCard;
            }
        }
        return TimeCard.EMPTY;
    }

    public void addTimeCard(final TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    public BigDecimal calculatePay(final Date date) {
        return null;
    }
}
