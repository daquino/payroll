package com.example.payroll.transaction.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Affiliation;
import com.example.payroll.model.Employee;
import com.example.payroll.model.UnionAffiliation;

import static com.example.payroll.db.PayrollDatabase.GlobalInstance.GlobalPayrollDatabase;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private final int memberId;
    private final double dues;

    public ChangeMemberTransaction(final int empId, final int memberId, final double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    protected void recordMembership(final Employee employee) {
        GlobalPayrollDatabase.addUnionMember(memberId, employee);
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }
}
