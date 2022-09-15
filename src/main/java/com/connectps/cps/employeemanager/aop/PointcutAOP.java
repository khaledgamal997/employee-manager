/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author Khaled.Gamal
 */
@Aspect
public class PointcutAOP {

    @Pointcut("execution( * com.connectps.cps.employeemanager.controller.EmployeeController.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution( * com.connectps.cps.employeemanager.rest.EmployeeRestController.*(..))")
    public void beforeRestControllerPointcut() {
    }

    @Pointcut("execution(* com.connectps.cps.employeemanager.rest.EmployeeRestController.get*(..))")
    public void afterReturnRestpointcut() {
    }

    @Pointcut("execution(* com.connectps.cps.employeemanager.rest.EmployeeRestController.findAll(..))")
    public void afterReturnRestFindPointcut() {

    }
}
