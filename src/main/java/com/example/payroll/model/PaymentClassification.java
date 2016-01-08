package com.example.payroll.model;

import java.math.BigDecimal;
import java.util.Date;

public interface PaymentClassification {
    BigDecimal calculatePay(Date date);

    PaymentClassification EMPTY = new EmptyPaymentClassification();
    class EmptyPaymentClassification implements PaymentClassification {

        public BigDecimal calculatePay(final Date date) {
            return BigDecimal.ZERO;
        }
    }
}
