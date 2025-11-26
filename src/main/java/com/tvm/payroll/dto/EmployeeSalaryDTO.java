package com.tvm.payroll.dto;

import com.tvm.payroll.entity.Employee;
import com.tvm.payroll.entity.Salary;

import java.time.LocalDate;
import java.util.List;

public class EmployeeSalaryDTO {

    private String employeeCode;
    private String name;
    private String designation;
    private String location;
    private String panNo;
    private LocalDate dob;;
    private LocalDate joiningDate;
    private List<Salary> salaries;
    private String bankAccount;

    public EmployeeSalaryDTO() {
    }

    // ✅ Constructor to map Employee → DTO fields
    public EmployeeSalaryDTO(Employee emp, List<Salary> salaries) {
        this.employeeCode = emp.getEmployeeCode();
        this.name = emp.getEmployeeName();
        this.designation = emp.getDesignation();
        this.location = emp.getLocation();
        this.joiningDate = emp.getJoiningDate();
        this.salaries = salaries;
        this.panNo= emp.getPanNo();
        this.dob= emp.getDob();
        this.bankAccount=emp.getBankAccount();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String pan) {
        this.panNo = pan;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // ✅ Getters and Setters
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
