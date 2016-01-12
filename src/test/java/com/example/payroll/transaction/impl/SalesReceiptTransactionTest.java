package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.SalesReceipt;
import com.example.payroll.model.impl.CommissionClassification;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SalesReceiptTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testSalesReceiptTransaction() throws Exception {
        int empId = 2;
        double commissionRate = 0.05;
        double salesAmount = 500.25;
        LocalDateTime date = LocalDateTime.now();
        transaction = new AddCommissionedEmployee(empId, "Bill", "Home", 50000, commissionRate);
        transaction.execute();
        transaction = new SalesReceiptTransaction(date, salesAmount, empId);
        transaction.execute();
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));
        CommissionClassification commissionClassification = (CommissionClassification) employee.getPaymentClassification();
        assertThat(commissionClassification, is(not(nullValue())));
        SalesReceipt salesReceipt = commissionClassification.getSalesReceipt(date);
        assertThat(salesReceipt, is(not(nullValue())));
        assertThat(salesReceipt.getAmount(), is(salesAmount));
    }

    @Test(expected = RuntimeException.class)
    public void testSalesReceiptTransactionFailsForNonExistingEmployee() throws Exception {
        int empId = 2;
        double salesAmount = 500.25;
        LocalDateTime date = LocalDateTime.now();
        transaction = new SalesReceiptTransaction(date, salesAmount, empId);
        transaction.execute();
    }

    @Test(expected = RuntimeException.class)
    public void testSalesReceiptTransactionFailsForNonCommissionedEmployee() throws Exception {
        int empId = 2;
        double salesAmount = 500.25;
        LocalDateTime date = LocalDateTime.now();
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        transaction = new SalesReceiptTransaction(date, salesAmount, empId);
        transaction.execute();
    }
}
