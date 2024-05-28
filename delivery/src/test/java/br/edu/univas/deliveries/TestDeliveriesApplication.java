package br.edu.univas.deliveries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

import br.edu.univas.si7.topicos.delivery.DeliveriesApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestDeliveriesApplication {
	
	public static void main(String[] args) {
		SpringApplication.from(DeliveriesApplication::main).with(TestDeliveriesApplication.class).run(args);
	}

}
