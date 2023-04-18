package com.exam.homework28.service;

import com.exam.homework28.exception.EmployeeNotFoundException;
import com.exam.homework28.model.Employee;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;


@Service
public class DepartmentService {
    EmployeeService employeeService = new EmployeeService();
    List<Employee> employeeList = new ArrayList<>(employeeService.findAll());

    public List<Employee> maxSalary(int department) throws EmployeeNotFoundException {
        return (List<Employee>) employeeList.stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public List<Employee> minSalary(int department) throws EmployeeNotFoundException {
        return (List<Employee>) employeeList.stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());


    }

    public List<Employee> returnAll(int department) {
        return employeeList.stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findAll() {
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

}

