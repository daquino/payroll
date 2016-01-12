package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.Paycheck;
import com.example.payroll.transaction.Transaction;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private final LocalDate payDate;
    private final Map<Integer, Paycheck> paychecks;
    public PaydayTransaction(final LocalDate payDate) {
        this.payDate = payDate;
        this.paychecks = new HashMap<Integer, Paycheck>();
    }

    public void execute() {
        Collection<Employee> employees = PayrollDatabase.getAllEmployees();
        for(Employee employee : employees) {
            if(employee.isPayDate(payDate)) {
                Paycheck paycheck = new Paycheck(employee.getPayPeriodStartDate(payDate), payDate);
                paychecks.put(employee.getId(), paycheck);
                employee.payDay(paycheck);
            }
        }
    }

    public LocalDate getPayPeriodEndDate() {
        return payDate;
    }

    public Paycheck getPaycheck(final int empId) {
        return paychecks.get(empId);
    }
}
