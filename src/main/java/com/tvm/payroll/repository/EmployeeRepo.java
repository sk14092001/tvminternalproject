package com.tvm.payroll.repository;

import com.tvm.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String code);

    @Query("SELECT COUNT(e) FROM Employee e")
    Long countAllEmployees();

    @Query("SELECT e FROM Employee e WHERE MONTH(e.joiningDate) = :month AND YEAR(e.joiningDate) = :year")
    List<Employee> findByJoiningMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT e FROM Employee e WHERE FUNCTION('MONTH', e.joiningDate) = :month AND FUNCTION('YEAR', e.joiningDate) = :year")
    List<Employee> findByJoiningMonthYear(@Param("month") int month, @Param("year") int year);

    // optional: fetch employees whose anniversary is today (day+month match)
    @Query("SELECT e FROM Employee e WHERE FUNCTION('DAY', e.joiningDate) = :day AND FUNCTION('MONTH', e.joiningDate) = :month")
    List<Employee> findByJoiningDayAndMonth(@Param("day") int day, @Param("month") int month);

    @Query("SELECT e FROM Employee e WHERE FUNCTION('DAY', e.dob) = :day AND FUNCTION('MONTH', e.dob) = :month")
    List<Employee> findByDobDayAndMonth(@Param("day") int day, @Param("month") int month);
}

