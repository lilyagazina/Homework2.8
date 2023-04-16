package com.exam.homework28.service;

import com.exam.homework28.exception.EmployeeAlreadyAddedException;
import com.exam.homework28.exception.EmployeeNotFoundException;
import com.exam.homework28.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    @GetMapping
    public Employee addEmployee(String firstName, String lastName) {
        // Добавить сотрудника.
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }


    @GetMapping
    public Employee removeEmployee(String firstName, String lastName) {
        // Удалить сотрудника.
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @GetMapping
    public Employee findEmployee(String firstName, String lastName) {
        //Найти сотрудника.
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

}


