package com.tvm.payroll.service.impl;

import com.tvm.payroll.dto.EmployeeRequest;
import com.tvm.payroll.dto.EmployeeSalaryUpdateRequest;
import com.tvm.payroll.dto.SalaryRequest;
import com.tvm.payroll.entity.Employee;
import com.tvm.payroll.entity.Salary;
import com.tvm.payroll.exception.BadRequestException;
import com.tvm.payroll.exception.ResourceNotFoundException;
import com.tvm.payroll.repository.EmployeeRepository;
import com.tvm.payroll.repository.SalaryRepository;
import com.tvm.payroll.dto.EmployeeSalaryDTO;
import com.tvm.payroll.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository empRepo;
    private final SalaryRepository salaryRepo;

    public EmployeeServiceImpl(EmployeeRepository empRepo, SalaryRepository salaryRepo) {
        this.empRepo = empRepo;
        this.salaryRepo = salaryRepo;
    }

    // 1️⃣ Create Employee
    @Transactional
    public Employee createEmployee(EmployeeRequest req) {
        if (req.getEmployeeCode() == null || req.getEmployeeCode().isEmpty()) {
            throw new BadRequestException("INVALID_EMPLOYEE_CODE", "Employee code must not be empty");
        }
        if (empRepo.findByEmployeeCode(req.getEmployeeCode()).isPresent()) {
            throw new BadRequestException("DUPLICATE_EMPLOYEE_CODE", "Employee code already exists: " + req.getEmployeeCode());
        }
        Employee emp = new Employee();
        emp.setEmployeeCode(req.getEmployeeCode());
        emp.setEmployeeName(req.getName());
        emp.setDesignation(req.getDesignation());
        emp.setLocation(req.getLocation());
        emp.setJoiningDate(req.getJoiningDate());
        emp.setPanNo(req.getPanNo());
        emp.setDob(req.getDob());
        emp.setBankAccount(req.getBankAccount());
        return empRepo.save(emp);
    }

    // 2️⃣ Get Employee
    public List<Employee> getAllEmployee() {
        List<Employee> employees = empRepo.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException(
                    "EMPLOYEES_NOT_FOUND",
                    "Employees not found"
            );
        }
        return employees;
    }


    // 2️⃣ Get Employee
    public Employee getEmployee(String employeeCode) {
        return empRepo.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EMPLOYEE_NOT_FOUND",
                        "Employee not found with employeeCode: " + employeeCode
                ));
    }

    // 3️⃣ Add or Update Salary
    @Transactional
    public Salary addOrUpdateEmployeeSalary(String employeeCode, SalaryRequest req) {
        Employee emp = getEmployee(employeeCode);

        if (req.getYear() == null || req.getSalaryMonth() == null) {
            throw new BadRequestException("INVALID_SALARY_PERIOD", "Year and month must not be null");
        }

        List<Salary> existing = salaryRepo.findSalary(employeeCode, req.getYear(), req.getSalaryMonth());
        Salary salary = existing.isEmpty() ? new Salary() : existing.get(0);

        salary.setEmployee(emp);
        salary.setYear(req.getYear());
        salary.setSalaryMonth(req.getSalaryMonth());
        salary.setNwd(req.getNwd());
        salary.setNol(req.getNol());

        // Earnings
        salary.setBasicSalary(defaultValue(req.getBasicSalary()));
        salary.setHouseRentAllowance(defaultValue(req.getHouseRentAllowance()));
        salary.setMedicalAllowance(defaultValue(req.getMedicalAllowance()));
        salary.setConveyanceAllowance(defaultValue(req.getConveyanceAllowance()));
        salary.setFlexiBenefitPlan(defaultValue(req.getFlexiBenefitPlan()));
        salary.setLeaveTravelAllowance(defaultValue(req.getLeaveTravelAllowance()));
        salary.setSpecialAllowance(defaultValue(req.getSpecialAllowance()));

        BigDecimal totalEarnings = salary.getBasicSalary()
                .add(salary.getHouseRentAllowance())
                .add(salary.getMedicalAllowance())
                .add(salary.getConveyanceAllowance())
                .add(salary.getFlexiBenefitPlan())
                .add(salary.getLeaveTravelAllowance())
                .add(salary.getSpecialAllowance());
        salary.setTotalEarnings(totalEarnings);

        // Deductions
        salary.setProfessionalTax(defaultValue(req.getProfessionalTax()));
        salary.setIncomeTax(defaultValue(req.getIncomeTax()));
        salary.setLeaveDeductions(defaultValue(req.getLeaveDeductions()));
        salary.setOtherDeductions(defaultValue(req.getOtherDeductions()));

        BigDecimal totalDeductions = salary.getProfessionalTax()
                .add(salary.getIncomeTax())
                .add(salary.getLeaveDeductions())
                .add(salary.getOtherDeductions());
        salary.setTotalDeductions(totalDeductions);

        // Net salary
        salary.setNetSalary(totalEarnings.subtract(totalDeductions));

        return salaryRepo.save(salary);
    }

    // 4️⃣ Get Employee with All Salaries
    public EmployeeSalaryDTO getEmployeeWithSalary(String employeeCode) {
        Employee emp = getEmployee(employeeCode);
        List<Salary> salaries = salaryRepo.findByEmployeeEmployeeCode(employeeCode);

        if (salaries.isEmpty()) {
            throw new ResourceNotFoundException("SALARY_NOT_FOUND", "No salary records found for employeeCode: " + employeeCode);
        }
        return new EmployeeSalaryDTO(emp, salaries);
    }

    // 5️⃣ Get Employee Salary by Month
    public EmployeeSalaryDTO getEmployeeSalaryByMonth(String employeeCode, Integer year, String month) {
        if (year == null || month == null) {
            throw new BadRequestException("INVALID_SALARY_PERIOD", "Year and month must not be null");
        }
        Employee emp = getEmployee(employeeCode);
        List<Salary> salaries = salaryRepo.findSalary(employeeCode, year, month);
        if (salaries.isEmpty()) {
            throw new ResourceNotFoundException("SALARY_NOT_FOUND", "No salary record found for the given period");
        }
        return new EmployeeSalaryDTO(emp, salaries);
    }

    // 6️⃣ Get All Employees with Salary Details
    public List<EmployeeSalaryDTO> getAllEmployeesWithSalary() {
        List<Employee> employees = empRepo.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("NO_EMPLOYEES", "No employees found in the system");
        }
        return employees.stream()
                .map(emp -> new EmployeeSalaryDTO(emp, salaryRepo.findByEmployeeEmployeeCode(emp.getEmployeeCode())))
                .toList();
    }

    // 7️⃣ Update only Employee Details
    @Transactional
    public Employee updateEmployeeDetails(String employeeCode, EmployeeRequest req) {
        Employee emp = getEmployee(employeeCode); // throws if not found

        if (req.getName() != null) emp.setEmployeeName(req.getName());
        if (req.getDesignation() != null) emp.setDesignation(req.getDesignation());
        if (req.getLocation() != null) emp.setLocation(req.getLocation());
        if (req.getPanNo() != null) emp.setPanNo(req.getPanNo());
        if (req.getBankAccount() != null) emp.setBankAccount(req.getBankAccount());
        if (req.getDob() != null) emp.setDob(req.getDob());
        if (req.getJoiningDate() != null) emp.setJoiningDate(req.getJoiningDate());

        return empRepo.save(emp);
    }
    // 8️⃣ Delete Employee
    @Transactional
    public void deleteEmployee(String employeeCode) {
        Employee emp = empRepo.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EMPLOYEE_NOT_FOUND",
                        "Employee not found with employeeCode: " + employeeCode
                ));
        empRepo.delete(emp);
    }

    // Helper
    private BigDecimal defaultValue(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
