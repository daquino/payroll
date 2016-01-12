package com.example.payroll.transaction;

import com.example.payroll.db.impl.InMemoryPayrollDatabase;
import org.junit.BeforeClass;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class BaseTransactionTest {

    @BeforeClass
    public static void setupClass() {
        GlobalPayrollDatabase = new InMemoryPayrollDatabase();
    }
}
