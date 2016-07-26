package com.example.payroll.transaction.impl;

import com.example.payroll.model.Employee;
import com.example.payroll.model.impl.CommissionClassification;
import com.example.payroll.transaction.Transaction;

import java.time.LocalDateTime;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class SalesReceiptTransaction implements Transaction {
    private LocalDateTime salesDate;
    private double salesAmount;
    private int empId;
    public SalesReceiptTransaction(final LocalDateTime salesDate, final double salesAmount, final int empId) {
        this.salesDate = salesDate;
        this.salesAmount = salesAmount;
        this.empId = empId;
    }

    public void execute() {
        Employee employee = GlobalPayrollDatabase.getEmployee(empId);
        if(employee != Employee.EMPTY) {
            if(employee.getPaymentClassification() instanceof  CommissionClassification) {
                CommissionClassification commissionClassification = (CommissionClassification) employee.getPaymentClassification();
                commissionClassification.addSalesReceipt(salesDate, salesAmount);
            }
            else {
                throw new RuntimeException("Tried to add sales receipt to non-commissioned employee.");
            }
        }
        else {
            throw new RuntimeException("No such employee.");
        }
    }
}
