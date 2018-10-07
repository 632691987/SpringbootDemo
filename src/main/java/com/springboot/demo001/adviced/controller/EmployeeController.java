package com.springboot.demo001.adviced.controller;

import com.springboot.demo001.adviced.bean.Employee;
import com.springboot.demo001.adviced.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.getEmp6(id).get();
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmployeeByName(lastName);
    }

    @PutMapping("/emp")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return "OK";
    }

}
