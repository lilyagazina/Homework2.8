package com.exam.homework28.controller;

import com.exam.homework28.model.Employee;
import com.exam.homework28.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int department) {
        return departmentService.maxSalary(department);
    }
    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam("departmentId") int department) {
        return departmentService.minSalary(department);
    }
    @GetMapping(value = "/return-all", params = "departmentId")
    public Collection returnAll(@RequestParam("departmentId") int department) {
        return departmentService.returnAll(department);
    }
    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> findAll() {
        return departmentService.findAll();
    }

}

