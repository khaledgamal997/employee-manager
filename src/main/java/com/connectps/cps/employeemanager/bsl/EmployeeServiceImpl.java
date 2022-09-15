/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.bsl;

import com.connectps.cps.employeemanager.dao.EmployeeRepository;
import com.connectps.cps.employeemanager.entities.Employee;
import com.connectps.cps.employeemanager.rest.EmployeeErrorResponse;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Khaled.Gamal
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //@Override
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> saveEmployee(Employee employee) {
        // return employeeRepository.save(employee);
        int id = employee.getId();
        Optional<Employee> opt = employeeRepository.findById(id);
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        if (opt.isPresent()) {
            error.setMessage("Employee already exists");
            error.setTiemStamp(System.currentTimeMillis());
            error.setStatus(HttpStatus.CONFLICT.value());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //@Override
    public void deletEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
        }
        catch (Exception e) {
            logger.log(Level.INFO, "No Employee with ID: '{'{0}'}' in DB", id);
        }

    }

    // @Override
    public ResponseEntity<Object> getEmployeeById(int id) {

        //getById => lazy loading while findById => eager loading
        //Optional<Employee> optEmployee = employeeRepository.findById(id);
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        /* if (optemployee.isPresent()) {
            empl.setId(optemployee.get().getId());
            empl.setFirstName(optemployee.get().getFirstName());
           empl.setLastName(optemployee.get().getLastName());
            empl.setTitle(optemployee.get().getTitle());
            return new ResponseEntity<>(empl, HttpStatus.OK);
        }*/

        if (optEmployee.isPresent()) {
            return new ResponseEntity<>(optEmployee.get(), HttpStatus.OK);
        }
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setMessage("employee not found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTiemStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Employee employee, int id) {
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (!optEmployee.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Employee theEmployee = optEmployee.get();
        theEmployee.setFirstName(employee.getFirstName());
        theEmployee.setLastName(employee.getLastName());
        theEmployee.setTitle(employee.getTitle());
        employeeRepository.save(theEmployee);

        return new ResponseEntity<>(theEmployee, HttpStatus.OK);

    }

}
