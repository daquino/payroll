package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteEmployeeTest {
    private Transaction transaction;
    private Employee employee;

    @Test
    public void testDeleteEmployee() throws Exception {
        int empId = 3;
        transaction = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        transaction.execute();

        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));

        transaction = new DeleteEmployeeTransaction(empId);
        transaction.execute();

        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee, is(nullValue()));

    }
}
