package com.example.payroll.db;

import com.example.payroll.model.Employee;

import java.util.Collection;

public interface PayrollDatabase {

    void addEmployee(final int itsEmpId, final Employee employee);

    Employee getEmployee(final int empId);

    void deleteEmployee(final int empId);

    void addUnionMember(final int memberId, final Employee employee);

    Employee getUnionMember(final int memberId);

    void removeUnionMember(final int memberId);

    Collection<Employee> getAllEmployees();

    class GlobalInstance {
        public static PayrollDatabase GlobalPayrollDatabase;
    }
}
