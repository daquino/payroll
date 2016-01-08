package com.example.payroll.transaction.impl;

import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.MonthlySchedule;
import com.example.payroll.model.impl.SalariedClassification;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private double itsSalary;
    public AddSalariedEmployee(int itsEmpId, String itsName, String itsAddress, double itsSalary) {
        super(itsEmpId, itsName, itsAddress);
        this.itsSalary = itsSalary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
