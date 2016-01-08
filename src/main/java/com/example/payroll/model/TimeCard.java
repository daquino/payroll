package com.example.payroll.model;

public class TimeCard {
    public static final TimeCard EMPTY = new TimeCard(0, 0);
    private final long date;
    private final double hours;

    public TimeCard(final long date, final double hours) {
        this.date = date;
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public long getDate() {
        return date;
    }
}
