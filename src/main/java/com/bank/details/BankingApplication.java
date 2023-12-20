package com.bank.details;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.bank.details"})
@SpringBootApplication
public class BankingApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankingApplication.class, args);
    }
}
