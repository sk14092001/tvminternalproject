package com.tvm.payroll.repository;

import com.tvm.payroll.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT s FROM Salary s WHERE s.employee.employeeCode = :employeeCode AND s.salaryYear = :year AND s.salaryMonth = :month")
    List<Salary> findSalary(@Param("employeeCode") String employeeCode,
                            @Param("year") Integer year,
                            @Param("month") String salaryMonth);


    // ✅ Find all salaries for a specific employee
    @Query("SELECT s FROM Salary s WHERE s.employee.employeeCode = :employeeCode")
    List<Salary> findByEmployeeEmployeeCode(@Param("employeeCode") String employeeCode);


}
