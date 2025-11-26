package com.tvm.payroll.service.impl;

import com.tvm.payroll.dto.DashboardResponse;
import com.tvm.payroll.dto.EmployeeSummary;
import com.tvm.payroll.dto.LeaveInfo;
import com.tvm.payroll.entity.Department;
import com.tvm.payroll.entity.Employee;
import com.tvm.payroll.entity.LeaveRequest;
import com.tvm.payroll.entity.LeaveStatus;
import com.tvm.payroll.repository.EmployeeRepo;
import com.tvm.payroll.repository.EmployeeRepository;
import com.tvm.payroll.repository.LeaveRequestRepository;
import com.tvm.payroll.repository.ProjectRepository;
import com.tvm.payroll.service.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepo employeeRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final ProjectRepository projectRepository;

    public DashboardServiceImpl(EmployeeRepo employeeRepository,
                                LeaveRequestRepository leaveRequestRepository,
                                ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public DashboardResponse getDashboard() {
        LocalDate today = LocalDate.now(); // server timezone - ensure configured to IST if required

        // 1. total employees
        long totalEmployees = employeeRepository.count();

        // 2 & 3: leave based present/absent
        long leavesToday = Optional.ofNullable(leaveRequestRepository.countByLeaveDate(today)).orElse(0L);
        long presentToday = Math.max(0L, totalEmployees - leavesToday);
        long absentToday = leavesToday;

        List<LeaveRequest> allLeaves = leaveRequestRepository.findAll(); // fetch everything

        List<LeaveInfo> leaveInfos = allLeaves.stream().map(l -> {
            LeaveInfo info = new LeaveInfo();
            info.setId(l.getId());
            info.setLeaveDate(l.getLeaveDate().toString());
            info.setLeaveNote(l.getLeaveNote());
            info.setStatus(l.getStatus().name());
            if (l.getEmployee() != null) {
                info.setEmployeeCode(l.getEmployee().getEmployeeCode());
                info.setEmployeeName(l.getEmployee().getEmployeeName());
            }
            return info;
        }).collect(Collectors.toList());


        // 5: new joinees this month
        YearMonth ym = YearMonth.from(today);
        List<Employee> joinees = employeeRepository.findByJoiningMonthAndYear(ym.getMonthValue(), ym.getYear());
        long newJoinees = joinees.size();

        // 6: department counts
        List<Employee> allEmployees = employeeRepository.findAll();
        Map<String, Long> deptCounts = Arrays.stream(Department.values())
                .collect(Collectors.toMap(Enum::name, d -> 0L)); // init with zeros
        allEmployees.forEach(e -> {
            Department d = e.getDepartment();
            String key = (d == null) ? "UNKNOWN" : d.name();
            deptCounts.put(key, deptCounts.getOrDefault(key, 0L) + 1);
        });

        // 7: total projects
        long projectCount = projectRepository.count();

        // 8: anniversaries today
        List<Employee> anniversaries = employeeRepository.findByJoiningDayAndMonth(today.getDayOfMonth(), today.getMonthValue());
        List<EmployeeSummary> annSummaries = anniversaries.stream().map(e -> {
            EmployeeSummary s = new EmployeeSummary();
            s.setEmployeeCode(e.getEmployeeCode());
            s.setName(e.getEmployeeName());
            s.setDesignation(e.getDesignation());
            return s;
        }).collect(Collectors.toList());

        // 9: birthdays today
        List<Employee> birthdays = employeeRepository.findByDobDayAndMonth(today.getDayOfMonth(), today.getMonthValue());
        List<EmployeeSummary> bdaySummaries = birthdays.stream().map(e -> {
            EmployeeSummary s = new EmployeeSummary();
            s.setEmployeeCode(e.getEmployeeCode());
            s.setName(e.getEmployeeName());
            s.setDesignation(e.getDesignation());
            return s;
        }).collect(Collectors.toList());

        DashboardResponse resp = new DashboardResponse();
        resp.setTotalEmployees(totalEmployees);
        resp.setPresentToday(presentToday);
        resp.setAbsentToday(absentToday);
        resp.setNewJoineesThisMonth(newJoinees);
        resp.setDepartmentCounts(deptCounts);
        resp.setTotalProjects(projectCount);
        resp.setLeaveRequests(leaveInfos);
        resp.setAnniversariesToday(annSummaries);
        resp.setBirthdaysToday(bdaySummaries);

        return resp;
    }
}
