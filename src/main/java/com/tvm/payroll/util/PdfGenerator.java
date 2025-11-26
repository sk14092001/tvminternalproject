package com.tvm.payroll.util;
import com.tvm.payroll.entity.Salary;
import java.io.OutputStream;
public interface PdfGenerator { void generateSalaryPdf(Salary salary, OutputStream out); }
