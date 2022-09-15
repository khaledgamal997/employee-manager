/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.connectps.cps.employeemanager.dao;

import com.connectps.cps.employeemanager.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Khaled.Gamal
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
