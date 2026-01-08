package com.springTestCrud.crudDemo.dao;

import com.springTestCrud.crudDemo.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{


    // Declaring EntityManager-----------------------------------------------------------

    private EntityManager entityManager;

    // Injecting Dependency using Autowiring---------------------------------------------------



    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    // All the overridden methods from Employee Interface-------------------------------------------

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee>query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee>list = query.getResultList();
        return list;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setId(0);
        Employee dbEmployee =  entityManager.merge(employee);
        return dbEmployee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        return dbEmployee;
    }

    @Override
    public String deleteEmployee(int id) {

        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        return "The deleted employee is " + employee;

    }
}
