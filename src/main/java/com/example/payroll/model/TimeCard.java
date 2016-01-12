package com.example.payroll.model;

import java.time.LocalDate;

public class TimeCard {
    public static final TimeCard EMPTY = new TimeCard(null, 0);
    private final LocalDate date;
    private final double hours;

    public TimeCard(final LocalDate date, final double hours) {
        this.date = date;
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }
}
