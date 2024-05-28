package br.edu.univas.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

import br.edu.univas.si7.topicos.customer.CustomerApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.from(CustomerApplication::main).with(TestCustomerApplication.class).run(args);
    }
}