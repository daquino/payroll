package com.example.payroll.model;

import java.time.LocalDateTime;

public interface PaymentSchedule {
    boolean isPayDate(LocalDateTime date);

    PaymentSchedule EMPTY = new EmptyPaymentSchedule();
    class EmptyPaymentSchedule implements PaymentSchedule {
        public boolean isPayDate(final LocalDateTime date) {
            return false;
        }
    }
}
