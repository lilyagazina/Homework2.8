package com.exam.homework28.service;

import com.exam.homework28.exception.EmployeeNotFoundException;
import com.exam.homework28.model.Employee;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Long.sum;
import static java.util.stream.Collectors.toMap;


@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /*List<Employee> employeeList = new ArrayList<>(employeeService.findAll());*/

    public Employee maxSalary(int department) {
        return employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee minSalary(int department) {
        return  employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }
   /* public List<Employee> averageSalary(int department)  {
        return List<Employee> employeeList.stream()
                .filter(emp -> emp.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
    }
    public List<Employee> totalSalary(int department) {
        return (List<Employee>) employeeList.stream()
                .filter(emp -> emp.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
*/
    public List<Employee> returnAll(int department) {
        return employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public void chanceDepartment(Employee employee, int newDepartment) {
        employeeService.findAll().stream()
                .filter(value -> Objects.equals(employee, value))
                .findFirst()
                .ifPresent(value -> value.setDepartment(newDepartment));
    }

    public Map<Integer, List<Employee>> findAll() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

}

