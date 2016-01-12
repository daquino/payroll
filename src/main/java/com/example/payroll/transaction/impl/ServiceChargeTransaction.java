package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;
import com.example.payroll.model.ServiceCharge;
import com.example.payroll.model.UnionAffiliation;
import com.example.payroll.transaction.Transaction;

import java.time.LocalDate;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class ServiceChargeTransaction implements Transaction {
    private final int memberId;
    private final LocalDate chargeDate;
    private final double amount;
    public ServiceChargeTransaction(final int memberId, final LocalDate chargeDate, final double amount) {
        this.memberId = memberId;
        this.chargeDate = chargeDate;
        this.amount = amount;
    }

    public void execute() {
        Employee employee = GlobalPayrollDatabase.getUnionMember(memberId);
        if(employee != null) {
            if(employee.getAffiliation() instanceof UnionAffiliation) {
                UnionAffiliation affiliation = (UnionAffiliation) employee.getAffiliation();
                affiliation.addServiceCharge(new ServiceCharge(chargeDate, amount));
            }
            else {
                throw new RuntimeException("Tried to add service charge for non-union employee.");
            }
        }
        else {
            throw new RuntimeException("No such employee.");
        }
    }
}
