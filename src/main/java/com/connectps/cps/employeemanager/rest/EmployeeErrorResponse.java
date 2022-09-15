/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connectps.cps.employeemanager.rest;

/**
 *
 * @author Khaled.Gamal
 */
public class EmployeeErrorResponse {

    private int status;
    private String message;
    private Long tiemStamp;

    public EmployeeErrorResponse() {
    }

    public EmployeeErrorResponse(int status, String message, Long tiemStamp) {
        this.status = status;
        this.message = message;
        this.tiemStamp = tiemStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTiemStamp() {
        return tiemStamp;
    }

    public void setTiemStamp(Long tiemStamp) {
        this.tiemStamp = tiemStamp;
    }

}
