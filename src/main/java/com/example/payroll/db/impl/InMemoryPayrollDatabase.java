package com.example.payroll.db.impl;

import com.example.payroll.db.PayrollDatabase;
import com.example.payroll.model.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPayrollDatabase implements PayrollDatabase {
    private static Map<Integer, Employee> employees = new ConcurrentHashMap<Integer, Employee>();
    private static Map<Integer, Employee> unionMembers = new ConcurrentHashMap<Integer, Employee>();

    public void addEmployee(final int itsEmpId, final Employee employee) {
        employees.put(itsEmpId, employee);
    }

    public Employee getEmployee(final int empId) {
        Employee employee = employees.get(empId);
        return employee != null ? employee : Employee.EMPTY;
    }

    public void deleteEmployee(final int empId) {
        employees.remove(empId);
    }

    public void addUnionMember(final int memberId, final Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public Employee getUnionMember(final int memberId) {
        Employee employee = unionMembers.get(memberId);
        return employee != null ? employee : Employee.EMPTY;
    }

    public void removeUnionMember(final int memberId) {
        unionMembers.remove(memberId);
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }
}
