package com.tvm.payroll.controller;

import com.tvm.payroll.dto.EmployeeRequest;
import com.tvm.payroll.dto.EmployeeSalaryDTO;
import com.tvm.payroll.dto.EmployeeSalaryUpdateRequest;
import com.tvm.payroll.entity.Employee;
import com.tvm.payroll.service.impl.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tvm.payroll.dto.SalaryRequest;
import com.tvm.payroll.entity.Salary;
import com.tvm.payroll.util.PdfGenerator;

@RestController
@RequestMapping("/api")
public class EmployeeSalaryController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeSalaryController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    // ✅ Create Employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest req) {
        return employeeService.createEmployee(req);
    }

    // ✅ Get Employee by EmployeeCode
    @GetMapping("/employees/{employeeCode}")
    public Employee getEmployee(@PathVariable String employeeCode) {
        return employeeService.getEmployee(employeeCode);
    }

    // ✅ Add or Update Salary for Employee
    @PostMapping("/employees/{employeeCode}/salaries")
    public Salary addOrUpdateEmployeeSalary(@PathVariable String employeeCode , @RequestBody SalaryRequest req) {
        return employeeService.addOrUpdateEmployeeSalary(employeeCode, req);
    }

    // ✅ Get Employee Salary Details (All Months)
    @GetMapping("/employees/{employeeCode}/salaries")
    public EmployeeSalaryDTO getEmployeeWithAllSalaries(@PathVariable String employeeCode) {
        return employeeService.getEmployeeWithSalary(employeeCode);
    }

    // ✅ Get Employee Salary Details by Year & Month
    @GetMapping("/employees/{employeeCode}/salaries/{year}/{month}")
    public ResponseEntity<EmployeeSalaryDTO> getEmployeeSalaryByMonth(
            @PathVariable String employeeCode,
            @PathVariable Integer year,
            @PathVariable String month) {

        EmployeeSalaryDTO dto = employeeService.getEmployeeSalaryByMonth(employeeCode, year, month);
        return ResponseEntity.ok(dto);
    }


    // ✅ Get All Employees with Salary Details
    @GetMapping("/employees-with-salaries")
    public List<EmployeeSalaryDTO> getAllEmployeesWithSalary() {
        return employeeService.getAllEmployeesWithSalary();
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    //Update Employee details
    @PutMapping("/employees/{employeeCode}")
    public ResponseEntity<Employee> updateEmployeeDetails(
            @PathVariable String employeeCode,
            @RequestBody EmployeeRequest req) {  // only employee details
        return ResponseEntity.ok(employeeService.updateEmployeeDetails(employeeCode, req));
    }

    // ✅ DELETE Employee by employeeCode
    @DeleteMapping("/employees/{employeeCode}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String employeeCode) {
        employeeService.deleteEmployee(employeeCode);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully: " + employeeCode);
        return ResponseEntity.ok(response);
    }



}

