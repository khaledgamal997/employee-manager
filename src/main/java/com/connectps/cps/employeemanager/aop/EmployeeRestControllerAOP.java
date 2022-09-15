/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.aop;

import com.connectps.cps.employeemanager.bsl.EmployeeService;
import com.connectps.cps.employeemanager.entities.Employee;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Khaled.Gamal
 */
@Aspect
@Component
public class EmployeeRestControllerAOP {

    @Autowired
    private EmployeeService employeService;
    private static final Logger logger = Logger.getLogger(EmployeeRestControllerAOP.class.getName());

    @Before("com.connectps.cps.employeemanager.aop.PointcutAOP.beforeRestControllerPointcut()")
    public void restBeforeAdvice(JoinPoint jp) {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        logger.log(Level.INFO, "Before advice on API methods signature is ==>{0}", ms.getName());
    }

    @Around("com.connectps.cps.employeemanager.aop.PointcutAOP.beforeRestControllerPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Employee employee;
        long start = System.currentTimeMillis();

        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        long res = end - start;
        try {

            logger.log(Level.INFO, "Around advice");
            logger.log(Level.INFO, "{0}", pjp.getSignature().getName());
            logger.log(Level.INFO, "takes {0} millisec to execute", res);
            if (obj instanceof ResponseEntity) {
                ResponseEntity response;
                response = (ResponseEntity) obj;
                if (response.getBody() instanceof Employee) {
                    employee = (Employee) response.getBody();
                    String title = employee.getTitle();
                    logger.log(Level.INFO, "Employee Tile is {0}", title);
                    if ("putEmployee".equals(pjp.getSignature().getName())
                            || "saveEmployee".equals(pjp.getSignature().getName())) {
                        String firstName = employee.getFirstName().trim();
                        String lastName = employee.getLastName().trim();
                        String titl = employee.getTitle().trim();

                        employee.setFirstName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
                        employee.setLastName(lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
                        employee.setTitle(titl.substring(0, 1).toUpperCase() + titl.substring(1));
                        employeService.updateEmployee(employee, employee.getId());
                        logger.log(Level.INFO, "All uppercased changed to {0}", employee.toString());

                    }
                }

            }
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @AfterReturning(pointcut = "com.connectps.cps.employeemanager.aop.PointcutAOP.afterReturnRestpointcut()"
            + "||"
            + "com.connectps.cps.employeemanager.aop.PointcutAOP.afterReturnRestFindPointcut()",
            returning = "result")
    public void restAfterReturnAdvice(JoinPoint jp, ResponseEntity result) {
        int httpStatus = result.getStatusCodeValue();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Object obj = result.getBody();
        logger.info("\n**Rest After Returning**");
        logger.log(Level.INFO, "Method => {0}\nFrom class ==>{1}"
                + "\nreturned ==> {2}"
                + "\nWith status Code: {3}", new Object[]{ms.getName(),
                    ms.getDeclaringTypeName(), obj, httpStatus});
    }

    @After("com.connectps.cps.employeemanager.aop.PointcutAOP.beforeRestControllerPointcut()")
    public void afterAdvice(JoinPoint jp) {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        String methodName = ms.getMethod().getName();
        Class[] params = ms.getParameterTypes();

        logger.log(Level.INFO, "method executed successfully {0} ",
                new Object[]{methodName});
        for (Class param : params) {
            logger.log(Level.INFO, "{0}", param);
        }
    }

    /* @AfterReturning(pointcut = "execution(* findAll(..))",
            returning = "result")
    public void afterReturningAdvice(JoinPoint jp, List<Employee> result
    ) {
        logger.info("After returning Advice ==> ");
        for (Employee e : result) {
            logger.info("id: [ " + e.getId() + " ]"
                    + " ,First name: [ " + e.getFirstName() + " ]"
                    + " ,Last name: [ " + e.getLastName() + " ]"
                    + " ,Title: [ " + e.getTitle() + " ]");
        }
    }*/
}
