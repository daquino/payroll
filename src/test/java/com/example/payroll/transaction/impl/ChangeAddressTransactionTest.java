package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeAddressTransactionTest {
    private Transaction transaction;

    @Test
    public void testChangeAddressTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 12.50);
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getAddress(), is("Home"));
        transaction = new ChangeAddressTransaction(empId, "Work");
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getAddress(), is("Work"));
    }
}
