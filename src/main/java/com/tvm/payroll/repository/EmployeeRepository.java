package com.tvm.payroll.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tvm.payroll.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    boolean existsByEmployeeCode(String employeeCode); // ✅ New lightweight check
}
