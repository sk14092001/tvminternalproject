package com.tvm.payroll.repository;

import com.tvm.payroll.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Long countBy();
    Long countByStatus(com.tvm.payroll.entity.ProjectStatus status);
    Long countByEmployeeEmployeeCode(String employeeCode);
}
