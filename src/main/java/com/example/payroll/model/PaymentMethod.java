package com.example.payroll.model;

public interface PaymentMethod {
    void sendPayment(int amount);

    PaymentMethod EMPTY = new EmptyPaymentMethod();
    class EmptyPaymentMethod implements PaymentMethod {
        public void sendPayment(final int amount) {

        }
    }
}
