package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.ServiceCharge;
import com.example.payroll.model.UnionAffiliation;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddServiceChargeTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testAddServiceCharge() throws Exception {
        int empId = 2;
        Transaction transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee, is(not(nullValue())));
        int memberId = 86;
        UnionAffiliation affiliation = new UnionAffiliation(memberId, 12.5);
        employee.setAffiliation(affiliation);
        GlobalPayrollDatabase.addUnionMember(memberId, employee);
        transaction = new ServiceChargeTransaction(memberId, LocalDate.of(2001, 11, 01), 12.95);
        transaction.execute();
        ServiceCharge serviceCharge = affiliation.getServiceCharge(LocalDate.of(2001, 11, 01));
        assertThat(serviceCharge.getAmount(), is(12.95));

    }
}
