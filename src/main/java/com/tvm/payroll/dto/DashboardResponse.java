package com.tvm.payroll.dto;

import java.util.List;
import java.util.Map;

public class DashboardResponse {
    private Long totalEmployees;
    private Long presentToday;
    private Long absentToday;
    private Long newJoineesThisMonth;
    private Long totalProjects;
    private Map<String, Long> departmentCounts; // FRONTEND/BACKEND/FULLSTACK counts

    private List<LeaveInfo> leaveRequests; // list of leaves (today or pending)
    private List<EmployeeSummary> anniversariesToday;
    private List<EmployeeSummary> birthdaysToday;


    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(Long presentToday) {
        this.presentToday = presentToday;
    }

    public Long getAbsentToday() {
        return absentToday;
    }

    public void setAbsentToday(Long absentToday) {
        this.absentToday = absentToday;
    }

    public Long getNewJoineesThisMonth() {
        return newJoineesThisMonth;
    }

    public void setNewJoineesThisMonth(Long newJoineesThisMonth) {
        this.newJoineesThisMonth = newJoineesThisMonth;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public Map<String, Long> getDepartmentCounts() {
        return departmentCounts;
    }

    public void setDepartmentCounts(Map<String, Long> departmentCounts) {
        this.departmentCounts = departmentCounts;
    }

    public List<LeaveInfo> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveInfo> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public List<EmployeeSummary> getAnniversariesToday() {
        return anniversariesToday;
    }

    public void setAnniversariesToday(List<EmployeeSummary> anniversariesToday) {
        this.anniversariesToday = anniversariesToday;
    }

    public List<EmployeeSummary> getBirthdaysToday() {
        return birthdaysToday;
    }

    public void setBirthdaysToday(List<EmployeeSummary> birthdaysToday) {
        this.birthdaysToday = birthdaysToday;
    }
}

