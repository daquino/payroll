package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteEmployeeTest extends BaseTransactionTest {
    private Transaction transaction;
    private Employee employee;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        int empId = 3;
        transaction = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        transaction.execute();

        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));

        transaction = new DeleteEmployeeTransaction(empId);
        transaction.execute();

        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(Employee.EMPTY));

    }
}
