package com.example.travelexpenses.services;

import java.util.List;

import com.example.travelexpenses.models.Employee;

public interface EmployeeService {

    public List<Employee> getAll();

    public Employee getById(Integer id);

    public Boolean save(Employee employee);

    public Integer getIdByEmail(String email);

    public Employee getEmail(String email);
}
