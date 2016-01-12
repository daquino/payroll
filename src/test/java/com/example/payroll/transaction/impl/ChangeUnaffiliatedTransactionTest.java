package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.NoAffiliation;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeUnaffiliatedTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeUnaffiliatedTransaction() throws Exception {
        int empId = 2;
        int memberId = 7734;
        double dues = 99.42;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        transaction = new ChangeMemberTransaction(empId, memberId, dues);
        transaction.execute();
        Employee member = GlobalPayrollDatabase.getUnionMember(memberId);
        assertThat(member, is(not(Employee.EMPTY)));
        transaction = new ChangeUnaffiliatedTransaction(empId);
        transaction.execute();
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));
        assertThat(employee.getAffiliation(), is(instanceOf(NoAffiliation.class)));
        member = GlobalPayrollDatabase.getUnionMember(memberId);
        assertThat(member, is(Employee.EMPTY));
    }
}
