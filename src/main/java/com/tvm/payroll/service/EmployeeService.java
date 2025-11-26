package com.tvm.payroll.service;

import com.tvm.payroll.dto.EmployeeRequest;
import com.tvm.payroll.dto.EmployeeSalaryDTO;
import com.tvm.payroll.dto.SalaryRequest;
import com.tvm.payroll.entity.Employee;
import com.tvm.payroll.entity.Salary;

import java.util.List;

public interface EmployeeService {

    // Create a new employee
    Employee createEmployee(EmployeeRequest req);

    // Get employee by employeeCode
    Employee getEmployee(String employeeCode);

    // Get employee with salary details
    EmployeeSalaryDTO getEmployeeWithSalary(String employeeCode);

    // Get all employees with salary details
    List<EmployeeSalaryDTO> getAllEmployeesWithSalary();

}
