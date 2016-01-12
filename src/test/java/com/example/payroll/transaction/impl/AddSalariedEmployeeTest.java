package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.HoldMethod;
import com.example.payroll.model.impl.MonthlySchedule;
import com.example.payroll.model.impl.SalariedClassification;
import com.example.payroll.transaction.BaseTransactionTest;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddSalariedEmployeeTest extends BaseTransactionTest {
    private AddSalariedEmployee transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testAddSalariedEmployee() throws Exception {
        int empId = 1;
        transaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        transaction.execute();

        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        assertThat(employee.getName(), is("Bob"));

        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        assertThat(salariedClassification, is(not(nullValue())));
        assertThat(salariedClassification.getSalary(), is(1000.0));

        MonthlySchedule monthlySchedule = (MonthlySchedule) employee.getPaymentSchedule();
        assertThat(monthlySchedule, is(not(nullValue())));

        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();
        assertThat(holdMethod, is(not(nullValue())));
    }
}
