package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeNameTransactionTest {
    private Transaction transaction;

    @Test
    public void testChangeNameTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 12.50);
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getName(), is("Bill"));
        transaction = new ChangeNameTransaction(empId, "Bob");
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getName(), is("Bob"));
    }
}
