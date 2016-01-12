package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabaseUtils;
import com.example.payroll.model.Paycheck;
import com.example.payroll.transaction.BaseTransactionTest;
import com.example.payroll.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaydayTransactionTest extends BaseTransactionTest {
    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        PayrollDatabaseUtils.clearDatabase();
    }

    @Test
    public void testPaySingleSalariedEmployee() throws Exception {
        int empId = 1;
        transaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getGrossPay(), is(1000.00));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(0.0));
        assertThat(paycheck.getNetPay(), is(1000.00));
    }

    @Test
    public void testPaySingleSalariedEmployeeOnWrongDate() throws Exception {
        int empId = 1;
        transaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 29);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        transaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(nullValue()));
    }


    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        validateHourlyPaycheck(paydayTransaction, empId, payDate, 0.0);
    }

    private void validateHourlyPaycheck(final PaydayTransaction paydayTransaction,
                                        final int empId, final LocalDate payDate, final double pay) {
        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getPayPeriodEndDate(), is(payDate));
        assertThat(paycheck.getGrossPay(), is(pay));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(0.0));
        assertThat(paycheck.getNetPay(), is(pay));
    }

    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        transaction = new TimeCardTransaction(payDate, 2, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 30.5);
    }

    @Test
    public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        transaction = new TimeCardTransaction(payDate, 9, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 144.88);
    }

    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 8);
        transaction = new TimeCardTransaction(payDate, 9, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(nullValue()));
    }

    @Test
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        transaction = new TimeCardTransaction(payDate, 2, empId);
        transaction.execute();
        transaction = new TimeCardTransaction(LocalDate.of(2001, 11, 8), 5, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 106.75);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        int empId = 2;
        transaction = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        transaction = new TimeCardTransaction(payDate, 2, empId);
        transaction.execute();
        transaction = new TimeCardTransaction(LocalDate.of(2001, 11, 2), 5, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, payDate, 30.50);
    }

    @Test
    public void testPaySinglCommissionedEmployeeNoSales() throws Exception {
        int empId = 1;
        transaction = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.10);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2015, 12, 11);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getGrossPay(), is(500.00));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(0.0));
        assertThat(paycheck.getNetPay(), is(500.00));
    }

    @Test
    public void testPaySinglCommissionedEmployeeOnWrongDate() throws Exception {
        int empId = 1;
        transaction = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.10);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2015, 12, 10);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(nullValue()));
    }

    @Test
    public void testPaySinglCommissionedEmployeeWithSingleSalesReceipt() throws Exception {
        int empId = 1;
        transaction = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.10);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2015, 12, 11);
        transaction = new SalesReceiptTransaction(LocalDateTime.of(payDate, LocalTime.NOON), 1000.00, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getGrossPay(), is(600.00));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(0.0));
        assertThat(paycheck.getNetPay(), is(600.00));
    }

    @Test
    public void testPaySinglCommissionedEmployeeWithMultipleSalesReceipt() throws Exception {
        int empId = 1;
        transaction = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.10);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2015, 12, 11);
        transaction = new SalesReceiptTransaction(LocalDateTime.of(payDate, LocalTime.NOON), 1000.00, empId);
        transaction.execute();
        transaction = new SalesReceiptTransaction(LocalDateTime.of(payDate.minusDays(1), LocalTime.NOON), 500.00, empId);
        transaction.execute();
        transaction = new SalesReceiptTransaction(LocalDateTime.of(payDate.minusDays(15), LocalTime.NOON), 250, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getGrossPay(), is(650.00));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(0.0));
        assertThat(paycheck.getNetPay(), is(650.00));
    }

    @Test
    public void testSalariedUnionMemberDues() throws Exception {
        int empId = 1;
        transaction = new AddSalariedEmployee(empId, "Bill", "Home", 1000.00);
        transaction.execute();
        int memberId = 7734;
        transaction = new ChangeMemberTransaction(empId, memberId, 100);
        transaction.execute();
        LocalDate payDate = LocalDate.of(2015, 11, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getGrossPay(), is(1000.00));
        assertThat(paycheck.getNetPay(), is(600.00));
    }

    @Test
    public void testHourlyUnionMemberServiceCharge() throws Exception {
        int empId = 1;
        int memberId = 7734;
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        transaction = new AddHourlyEmployee(empId, "Bob", "Home", 15.42);
        transaction.execute();
        transaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        transaction.execute();
        transaction = new ServiceChargeTransaction(memberId, payDate, 19.42);
        transaction.execute();
        transaction = new TimeCardTransaction(payDate, 8, empId);
        transaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertThat(paycheck, is(not(nullValue())));
        assertThat(paycheck.getPayPeriodEndDate(), is(payDate));
        assertThat(paycheck.getGrossPay(), is(123.36));
        assertThat(paycheck.getField("Disposition"), is("Hold"));
        assertThat(paycheck.getDeductions(), is(28.84));
        assertThat(paycheck.getNetPay(), is(94.52));
    }
}