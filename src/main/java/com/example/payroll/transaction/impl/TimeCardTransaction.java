package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.HourlyClassification;
import com.example.payroll.transaction.Transaction;

import java.time.LocalDate;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class TimeCardTransaction implements Transaction {
    private final LocalDate date;
    private final double hours;
    private final int empId;

    public TimeCardTransaction(final LocalDate date, final double hours, final int empId) {
        this.date = date;
        this.hours = hours;
        this.empId = empId;
    }

    public void execute() {
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        if(employee != Employee.EMPTY) {
            if(employee.getPaymentClassification() instanceof HourlyClassification) {
                HourlyClassification classification = (HourlyClassification) employee.getPaymentClassification();
                classification.addTimeCard(date, hours);
            }
            else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee.");
            }
        }
        else {
            throw new RuntimeException("No such employee.");
        }
    }
}
