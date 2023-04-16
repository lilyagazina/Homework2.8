package com.exam.homework28.service;

import com.exam.homework28.model.Employee;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentService {
    EmployeeService employeeService = new EmployeeService();

    public List<Employee> maxSalary(int department) {
        List<Employee> employeeList = new ArrayList<>(employeeService.findAll());
        return employeeList;
    }

    public Employee minSalary(int department) {
        return null;
    }

    public Employee returnAll(int department) {
        return null;
    }
}

