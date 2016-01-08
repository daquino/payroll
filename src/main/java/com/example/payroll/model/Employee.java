package com.example.payroll.model;

public class Employee {
    public static final Employee EMPTY = new Employee(-1, "John Doe", "Nowhere", PaymentClassification.EMPTY,
            PaymentSchedule.EMPTY, PaymentMethod.EMPTY, Affiliation.EMPTY);
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
}
