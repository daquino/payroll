package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.MailMethod;
import com.example.payroll.model.PaymentMethod;
import com.example.payroll.transaction.Transaction;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChangeMailTransactionTest {
    private Transaction transaction;

    @Test
    public void testChangeMailTransaction() throws Exception {
        int empId = 1;
        Employee employee;
        transaction = new AddHourlyEmployee(empId, "Bob", "home", 25.75);
        transaction.execute();
        employee = PayrollDatabase.getEmployee(empId);
        employee.setPaymentMethod(PaymentMethod.EMPTY);

        transaction = new ChangeMailTransaction(empId, "1234 Test Street");
        transaction.execute();

        MailMethod paymentMethod = (MailMethod) employee.getPaymentMethod();
        assertThat(paymentMethod.getAddress(), is("1234 Test Street"));
    }
}
