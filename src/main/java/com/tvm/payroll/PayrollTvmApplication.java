package com.tvm.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tvm.payroll")
public class PayrollTvmApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayrollTvmApplication.class, args);
    }
}
