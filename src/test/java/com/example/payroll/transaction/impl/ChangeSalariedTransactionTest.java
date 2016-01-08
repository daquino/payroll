package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.HourlyClassification;
import com.example.payroll.model.impl.MonthlySchedule;
import com.example.payroll.model.impl.SalariedClassification;
import com.example.payroll.model.impl.WeeklySchedule;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class ChangeSalariedTransactionTest {
    private Transaction transaction;

    @Test
    public void testChangeSalariedTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bob", "Home", 27.52);
        transaction.execute();

        employee = PayrollDatabase.getEmployee(empId);
        assertThat(employee.getPaymentClassification(), is(instanceOf(HourlyClassification.class)));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklySchedule.class)));

        transaction = new ChangeSalariedTransaction(empId, 75000.00);
        transaction.execute();
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        assertThat(salariedClassification.getSalary(), is(75000.00));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlySchedule.class)));
    }
}
