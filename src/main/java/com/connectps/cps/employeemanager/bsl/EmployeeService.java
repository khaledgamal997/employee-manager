/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.connectps.cps.employeemanager.bsl;

import com.connectps.cps.employeemanager.entities.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Khaled.Gamal
 */
public interface EmployeeService {

    public ResponseEntity<List<Employee>> findAll();

    public ResponseEntity<Object> getEmployeeById(int id);

    public ResponseEntity<Object> saveEmployee(Employee employee);

    public ResponseEntity<Employee> updateEmployee(Employee employee, int id);

    public void deletEmployee(int id);
}
