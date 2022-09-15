/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.rest;

import com.connectps.cps.employeemanager.bsl.EmployeeService;
import com.connectps.cps.employeemanager.entities.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Khaled.Gamal
 */
@RestController
@RequestMapping(path = "/api/employees", produces = "application/json")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {

        return employeeService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id) {

        return employeeService.getEmployeeById(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> putEmployee(@RequestBody Employee employee, @PathVariable int id) {
        return employeeService.updateEmployee(employee, id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deletEmployee(id);

        }
        catch (EmptyResultDataAccessException e) {

        }
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handelException(Exception e) {
        EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTiemStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
