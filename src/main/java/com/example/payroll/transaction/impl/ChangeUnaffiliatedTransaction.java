package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Affiliation;
import com.example.payroll.model.Employee;
import com.example.payroll.model.UnionAffiliation;
import com.example.payroll.model.impl.NoAffiliation;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(final int empId) {
        super(empId);
    }

    @Override
    protected void recordMembership(final Employee employee) {
        if(employee.getAffiliation() instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
            GlobalPayrollDatabase.removeUnionMember(unionAffiliation.getMemberId());
        }
    }

    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}
