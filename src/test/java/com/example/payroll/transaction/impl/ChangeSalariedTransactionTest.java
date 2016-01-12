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

public class ChangeSalariedTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeSalariedTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bob", "Home", 27.52);
        transaction.execute();

        employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee.getPaymentClassification(), is(instanceOf(HourlyClassification.class)));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklySchedule.class)));

        transaction = new ChangeSalariedTransaction(empId, 75000.00);
        transaction.execute();
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        assertThat(salariedClassification.getSalary(), is(75000.00));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlySchedule.class)));
    }
}
