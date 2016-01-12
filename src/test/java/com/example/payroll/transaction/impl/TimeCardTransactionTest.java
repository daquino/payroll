package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.TimeCard;
import com.example.payroll.model.impl.HourlyClassification;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeCardTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testTimeCardTransaction() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        transaction = new TimeCardTransaction(LocalDate.of(2015, 12, 28), 8.0, empId);
        transaction.execute();
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));
        HourlyClassification classification = (HourlyClassification) employee.getPaymentClassification();
        assertThat(classification, is(not(nullValue())));
        TimeCard timeCard = classification.getTimeCard(LocalDate.of(2015, 12, 28));
        assertThat(timeCard, is(not(nullValue())));
        assertThat(timeCard.getHours(), is(8.0));
    }

    @Test(expected = RuntimeException.class)
    public void testTimeCardTransactionFailsForNonExistingEmployee() throws Exception {
        int empId = 2;
        transaction = new TimeCardTransaction(LocalDate.of(2015, 12, 28), 8.0, empId);
        transaction.execute();
    }

    @Test(expected = RuntimeException.class)
    public void testTimeCardTransactionFailsForNonHourlyEmployee() throws Exception {
        int empId = 2;
        transaction = new AddSalariedEmployee(empId, "Bill", "Home", 2500);
        transaction.execute();
        transaction = new TimeCardTransaction(LocalDate.of(2015, 12, 28), 8.0, empId);
        transaction.execute();
    }
}
