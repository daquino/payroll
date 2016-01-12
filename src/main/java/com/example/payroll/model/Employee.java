package com.example.payroll.model;

import com.example.payroll.model.impl.EmptyPaymentClassification;
import com.example.payroll.model.impl.EmptyPaymentMethod;
import com.example.payroll.model.impl.EmptyPaymentSchedule;
import com.example.payroll.model.impl.NoAffiliation;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    public static final Employee EMPTY = new Employee(-1, "John Doe", "Nowhere", new EmptyPaymentClassification(),
            new EmptyPaymentSchedule(), new EmptyPaymentMethod(), new NoAffiliation());
    private Integer id;
    private String name;
    private String address;
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private Affiliation affiliation;

    public Employee(final Integer id, final String name, final String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.affiliation = new NoAffiliation();
    }

    private Employee(final Integer id, final String name, final String address,
                     PaymentClassification classification, PaymentSchedule schedule, PaymentMethod method,
                     Affiliation affiliation) {
        this(id, name, address);
        this.paymentClassification = classification;
        this.paymentSchedule = schedule;
        this.paymentMethod = method;
        this.affiliation = affiliation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(final PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(final PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(final Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Integer(id));
    }

    public void payDay(final Paycheck paycheck) {
        double grossPay = paymentClassification.calculatePay(paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate());
        double deductions = affiliation.calculateDeductions(paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate());
        double netPay = grossPay - deductions;
        paycheck.setGrossPay(grossPay);
        paycheck.setDeductions(deductions);
        paycheck.setNetPay(netPay);
        paymentMethod.pay(paycheck);
    }

    public boolean isPayDate(LocalDate date) {
        return paymentSchedule.isPayDate(date);
    }

    public LocalDate getPayPeriodStartDate(final LocalDate payDate) {
        return paymentSchedule.getPayPeriodStartDate(payDate);
    }
}
