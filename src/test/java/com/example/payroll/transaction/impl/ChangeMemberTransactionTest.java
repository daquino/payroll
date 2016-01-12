package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Affiliation;
import com.example.payroll.model.Employee;
import com.example.payroll.model.UnionAffiliation;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeMemberTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeMemberTransaction() throws Exception {
        int empId = 2;
        int memberId = 7734;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        transaction = new ChangeMemberTransaction(empId, memberId, 99.42);
        transaction.execute();
        Employee employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));
        Affiliation affiliation = employee.getAffiliation();
        assertThat(affiliation, is(not(nullValue())));
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
        assertThat(unionAffiliation.getDues(), is(99.42));
        Employee member = PayrollDatabase.getUnionMember(memberId);
        assertThat(employee, is(member));
    }
}
