package com.springTestCrud.crudDemo.services;

import com.springTestCrud.crudDemo.entities.Employee;

import java.util.List;


public interface EmployeeService {

    Employee findById(int id);
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    String deleteEmployee(int id);
}
