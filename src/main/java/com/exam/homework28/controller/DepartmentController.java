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
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Collection maxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.maxSalary(departmentId);
    }
    @GetMapping(path = "/min-salary")
    public Collection minSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.minSalary(departmentId);
    }
    @GetMapping(path = "/return-all")
    public Collection returnAll(@RequestParam("departmentId") int departmentId) {
        return departmentService.returnAll(departmentId);
    }
    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> findAll() {
        return departmentService.findAll();
    }

}

