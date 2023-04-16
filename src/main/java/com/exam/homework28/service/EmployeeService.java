package com.exam.homework28.service;

import com.exam.homework28.exception.EmployeeAlreadyAddedException;
import com.exam.homework28.exception.EmployeeNotFoundException;
import com.exam.homework28.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }


    public Employee remove(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public Employee find(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return employeeList;
    }


}


