package com.example.payroll.util;

import java.time.LocalDate;

public class PaymentDateUtils {
    public static boolean isInPayPeriod(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        return (currentDate.isAfter(startDate) || currentDate.isEqual(startDate)) &&
                (currentDate.isBefore(endDate) || currentDate.isEqual(endDate));
    }
}
