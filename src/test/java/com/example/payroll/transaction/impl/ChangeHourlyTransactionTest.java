package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.HourlyClassification;
import com.example.payroll.model.impl.MonthlySchedule;
import com.example.payroll.model.impl.SalariedClassification;
import com.example.payroll.model.impl.WeeklySchedule;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class ChangeHourlyTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddSalariedEmployee(empId, "Bob", "Home", 75000);
        transaction.execute();

        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee.getPaymentClassification(), is(instanceOf(SalariedClassification.class)));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlySchedule.class)));

        transaction = new ChangeHourlyTransaction(empId, 27.52);
        transaction.execute();
        HourlyClassification classification = (HourlyClassification) employee.getPaymentClassification();
        assertThat(classification.getHourlyRate(), is(27.52));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklySchedule.class)));
    }
}
