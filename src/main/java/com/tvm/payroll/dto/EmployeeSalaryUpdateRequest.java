package com.tvm.payroll.dto;

public class EmployeeSalaryUpdateRequest {
    private EmployeeRequest employee;  // optional
    private SalaryRequest salary;      // optional

    // Getters & Setters
    public EmployeeRequest getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeRequest employee) {
        this.employee = employee;
    }

    public SalaryRequest getSalary() {
        return salary;
    }

    public void setSalary(SalaryRequest salary) {
        this.salary = salary;
    }
}
