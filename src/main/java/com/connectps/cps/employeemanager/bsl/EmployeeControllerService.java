/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.bsl;

import com.connectps.cps.employeemanager.dao.EmployeeRepository;
import com.connectps.cps.employeemanager.entities.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Khaled.Gamal
 */
@Service
public class EmployeeControllerService {

    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> findAll() {

        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        return opt;
    }

    public void deletEmployee(int id) {
        employeeRepository.deleteById(id);
    }

}
