package com.example.payroll.db;

import com.example.payroll.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayrollDatabase {
    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    private static Map<Integer, Employee> unionMembers = new ConcurrentHashMap<Integer, Employee>();

    public static void addEmployee(final int itsEmpId, final Employee employee) {
        employees.put(itsEmpId, employee);
    }

    public static Employee getEmployee(final int empId) {
        Employee employee = employees.get(empId);
        return employee != null ? employee : Employee.EMPTY;
    }

    public static void deleteEmployee(final int empId) {
        employees.remove(empId);
    }

    public static void addUnionMember(final int memberId, final Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public static Employee getUnionMember(final int memberId) {
        Employee employee = unionMembers.get(memberId);
        return employee != null ? employee : Employee.EMPTY;
    }
}
