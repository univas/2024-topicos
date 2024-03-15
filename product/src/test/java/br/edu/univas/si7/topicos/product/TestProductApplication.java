package br.edu.univas.si7.topicos.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProductApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductApplication::main).with(TestProductApplication.class).run(args);
	}

}
