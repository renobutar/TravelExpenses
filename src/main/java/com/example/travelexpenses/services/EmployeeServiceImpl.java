package com.example.travelexpenses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelexpenses.models.Employee;
import com.example.travelexpenses.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
    }

    @Override
    public Boolean save(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).isPresent(); 
    }

    @Override
    public Integer getIdByEmail(String email) {
        return employeeRepository.findIdByEmail(email);
    }

    @Override
    public Employee getEmail(String email) {
        return employeeRepository.findEmail(email);
    }
    
}
