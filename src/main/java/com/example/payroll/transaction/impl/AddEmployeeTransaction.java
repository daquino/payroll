package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.PaymentClassification;
import com.example.payroll.model.PaymentMethod;
import com.example.payroll.model.PaymentSchedule;
import com.example.payroll.model.impl.HoldMethod;
import com.example.payroll.transaction.Transaction;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public abstract class AddEmployeeTransaction implements Transaction {
    private final int itsEmpId;
    private final String itsName;
    private final String itsAddress;

    public AddEmployeeTransaction(final int itsEmpId, final String itsName, final String itsAddress) {
        this.itsEmpId = itsEmpId;
        this.itsName = itsName;
        this.itsAddress = itsAddress;
    }

    public void execute() {
        PaymentClassification paymentClassification = getClassification();
        PaymentSchedule paymentSchedule = getSchedule();
        PaymentMethod paymentMethod = new HoldMethod();
        Employee employee = new Employee(itsEmpId, itsName, itsAddress);
        employee.setPaymentClassification(paymentClassification);
        employee.setPaymentSchedule(paymentSchedule);
        employee.setPaymentMethod(paymentMethod);
        GlobalPayrollDatabase.addEmployee(itsEmpId, employee);
    }

    abstract protected PaymentClassification getClassification();
    abstract protected PaymentSchedule getSchedule();
}
