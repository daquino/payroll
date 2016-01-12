package com.example.payroll.model;

public class DirectMethod implements PaymentMethod {
    private final String bank;
    private final String account;

    public DirectMethod(final String bank, final String account) {
        this.bank = bank;
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }

    public void pay(final Paycheck paycheck) {

    }
}
