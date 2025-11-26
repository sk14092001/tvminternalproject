package com.tvm.payroll.dto;
import java.math.BigDecimal;
public class SalaryRequest {
    public Long employeeId;
    public Integer year;
    public String salaryMonth;
    public Integer nwd; // Number of working days
    public Integer nol; // Number of leaves

    // Earnings
    public BigDecimal basicSalary;
    public BigDecimal houseRentAllowance;
    public BigDecimal medicalAllowance;
    public BigDecimal conveyanceAllowance;
    public BigDecimal flexiBenefitPlan;
    public BigDecimal leaveTravelAllowance;
    public BigDecimal specialAllowance;

    // Deductions
    public BigDecimal professionalTax;
    public BigDecimal incomeTax;

    public BigDecimal leaveDeductions;
    public BigDecimal otherDeductions;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
}
