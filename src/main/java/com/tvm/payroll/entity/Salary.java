package com.tvm.payroll.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "salary",
        indexes = {
                @Index(name = "IDX_SALARY_YEAR_MONTH", columnList = "salary_year, salary_month")
        }
)
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "salary_year")
    private Integer salaryYear;

    @Column(name = "salary_month")
    private String salaryMonth;

    private Integer nwd;
    private Integer nol;

    private BigDecimal basicSalary;
    private BigDecimal houseRentAllowance;
    private BigDecimal medicalAllowance;
    private BigDecimal conveyanceAllowance;
    private BigDecimal flexiBenefitPlan;
    private BigDecimal leaveTravelAllowance;
    private BigDecimal specialAllowance;

    private BigDecimal professionalTax;
    private BigDecimal incomeTax;
    private BigDecimal leaveDeductions;
    private BigDecimal otherDeductions;
    private BigDecimal totalEarnings;
    private BigDecimal totalDeductions;
    private BigDecimal netSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",
            foreignKey = @ForeignKey(
                    name = "fk_salary_employee",
                    foreignKeyDefinition = "FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE"))
    @JsonIgnore // ✅ Add this
    private Employee employee;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return salaryYear;
    }

    public void setYear(Integer year) {
        this.salaryYear = year;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Integer getNwd() {
        return nwd;
    }

    public void setNwd(Integer nwd) {
        this.nwd = nwd;
    }

    public Integer getNol() {
        return nol;
    }

    public void setNol(Integer nol) {
        this.nol = nol;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getHouseRentAllowance() {
        return houseRentAllowance;
    }

    public void setHouseRentAllowance(BigDecimal houseRentAllowance) {
        this.houseRentAllowance = houseRentAllowance;
    }

    public BigDecimal getMedicalAllowance() {
        return medicalAllowance;
    }

    public void setMedicalAllowance(BigDecimal medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    public BigDecimal getConveyanceAllowance() {
        return conveyanceAllowance;
    }

    public void setConveyanceAllowance(BigDecimal conveyanceAllowance) {
        this.conveyanceAllowance = conveyanceAllowance;
    }

    public BigDecimal getFlexiBenefitPlan() {
        return flexiBenefitPlan;
    }

    public void setFlexiBenefitPlan(BigDecimal flexiBenefitPlan) {
        this.flexiBenefitPlan = flexiBenefitPlan;
    }

    public BigDecimal getLeaveTravelAllowance() {
        return leaveTravelAllowance;
    }

    public void setLeaveTravelAllowance(BigDecimal leaveTravelAllowance) {
        this.leaveTravelAllowance = leaveTravelAllowance;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public BigDecimal getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(BigDecimal professionalTax) {
        this.professionalTax = professionalTax;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getLeaveDeductions() {
        return leaveDeductions;
    }

    public void setLeaveDeductions(BigDecimal leaveDeductions) {
        this.leaveDeductions = leaveDeductions;
    }

    public BigDecimal getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(BigDecimal otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
