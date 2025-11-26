package com.tvm.payroll.repository;

// package com.tvm.payroll.repository;
import com.tvm.payroll.entity.LeaveRequest;
import com.tvm.payroll.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT COUNT(l) FROM LeaveRequest l WHERE l.leaveDate = :date")
    Long countByLeaveDate(LocalDate date);

    List<LeaveRequest> findByLeaveDate(LocalDate date);

    List<LeaveRequest> findByStatus(LeaveStatus status);


}

