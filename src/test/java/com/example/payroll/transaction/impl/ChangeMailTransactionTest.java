package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Employee;
import com.example.payroll.model.MailMethod;
import com.example.payroll.model.impl.EmptyPaymentMethod;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChangeMailTransactionTest extends BaseTransactionTest {
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testChangeMailTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bob", "home", 25.75);
        transaction.execute();
        employee = GlobalPayrollDatabase.getEmployee(empId);
        employee.setPaymentMethod(new EmptyPaymentMethod());

        transaction = new ChangeMailTransaction(empId, "1234 Test Street");
        transaction.execute();

        MailMethod paymentMethod = (MailMethod) employee.getPaymentMethod();
        assertThat(paymentMethod.getAddress(), is("1234 Test Street"));
    }
}
