package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.*;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddCommissionedEmployeeTest {
    private Transaction transaction;

    @Test
    public void testAddCommissionedEmployee() throws Exception {
        int empId = 1;
        transaction = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.15);
        transaction.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getName(), is("Bob"));

        CommissionClassification commissionClassification = (CommissionClassification) employee.getPaymentClassification();
        assertThat(commissionClassification, is(not(nullValue())));
        assertThat(commissionClassification.getSalary(), is(1000.0));
        assertThat(commissionClassification.getCommissionRate(), is(0.15));

        BiWeeklySchedule biWeeklySchedule = (BiWeeklySchedule) employee.getPaymentSchedule();
        assertThat(biWeeklySchedule, is(not(nullValue())));

        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();
        assertThat(holdMethod, is(not(nullValue())));
    }
}
