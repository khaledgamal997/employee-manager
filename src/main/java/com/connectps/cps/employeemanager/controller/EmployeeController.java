/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.controller;

import com.connectps.cps.employeemanager.bsl.EmployeeControllerService;
import com.connectps.cps.employeemanager.bsl.EmployeeServiceImpl;
import com.connectps.cps.employeemanager.entities.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Khaled.Gamal
 */
@Controller

@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeControllerService employeeService;

    @Autowired
    public EmployeeController(EmployeeControllerService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employeeList", employees);
        return "list-employees";
    }

    @GetMapping("/add-employee-form")
    public String addEmploye(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";

    }

    @GetMapping("/update-form")
    public String showUpdateForm(@RequestParam("employeeId") int id, Model model) {
        Optional<Employee> optemployee = employeeService.getEmployeeById(id);
        Employee employee = optemployee.get();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deletEmployee(id);
        return "redirect:/employees";
    }

}
