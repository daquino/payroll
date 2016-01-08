package com.example.payroll.db;

import com.example.payroll.model.Employee;

import java.lang.reflect.Field;
import java.util.Map;

public class PayrollDatabaseUtils {
    public static void clearDatabase() throws NoSuchFieldException, IllegalAccessException {
        Field field = PayrollDatabase.class.getDeclaredField("employees");
        field.setAccessible(true);
        Map<Long, Employee> employees = (Map<Long, Employee>) field.get(null);
        employees.clear();
    }
}
