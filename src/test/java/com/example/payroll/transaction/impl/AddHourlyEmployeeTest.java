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

public class AddHourlyEmployeeTest {
    private Transaction transaction;

    @Test
    public void testAddHourlyEmployee() throws Exception {
        int empId = 1;
        transaction = new AddHourlyEmployee(empId, "Bob", "Home", 10.50);
        transaction.execute();

        Employee employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getName(), is("Bob"));

        HourlyClassification hourlyClassification = (HourlyClassification) employee.getPaymentClassification();
        assertThat(hourlyClassification, is(not(nullValue())));
        assertThat(hourlyClassification.getHourlyRate(), is(10.50));

        WeeklySchedule weeklySchedule = (WeeklySchedule) employee.getPaymentSchedule();
        assertThat(weeklySchedule, is(not(nullValue())));

        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();
        assertThat(holdMethod, is(not(nullValue())));

    }
}
