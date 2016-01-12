package com.example.payroll.transaction.impl;

import com.example.payroll.model.Affiliation;
import com.example.payroll.model.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(final int empId) {
        super(empId);
    }

    @Override
    protected void change(final Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }

    protected abstract void recordMembership(final Employee employee);

    protected abstract Affiliation getAffiliation();

}
