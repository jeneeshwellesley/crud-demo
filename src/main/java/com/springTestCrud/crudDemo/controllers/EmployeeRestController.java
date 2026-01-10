package com.springTestCrud.crudDemo.controllers;


import com.springTestCrud.crudDemo.entities.Employee;
import com.springTestCrud.crudDemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController{
    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController (EmployeeService employeeService, JsonMapper jsonMapper){
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.addEmployee(employee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.updateEmployee(employee);
        return dbEmployee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee updateEmployeeFields(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){

        Employee dbEmployee = employeeService.findById(employeeId);

        Employee patchedEmployee = jsonMapper.updateValue(dbEmployee, patchPayload);
        Employee dEmployee = employeeService.updateEmployee(patchedEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee dbEmployee = employeeService.findById(employeeId);
        return employeeService.deleteEmployee(employeeId);
    }




}
