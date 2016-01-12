package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeAddressTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeAddressTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 12.50);
        transaction.execute();
        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee.getAddress(), is("Home"));
        transaction = new ChangeAddressTransaction(empId, "Work");
        transaction.execute();
        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee.getAddress(), is("Work"));
    }
}
