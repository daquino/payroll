package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.DirectMethod;
import com.example.payroll.model.Employee;
import com.example.payroll.model.PaymentMethod;
import com.example.payroll.model.impl.EmptyPaymentMethod;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChangeDirectTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeDirectTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bob", "home", 25.75);
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        employee.setPaymentMethod(new EmptyPaymentMethod());

        transaction = new ChangeDirectTransaction(empId, "US Bank", "123456789123");
        transaction.execute();

        DirectMethod paymentMethod = (DirectMethod) employee.getPaymentMethod();
        assertThat(paymentMethod.getBank(), is("US Bank"));
        assertThat(paymentMethod.getAccount(), is("123456789123"));
    }
}
