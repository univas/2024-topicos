package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

import br.edu.univas.si7.topicos.demo.TrabalhoApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestTrabalhoApplication {

	public static void main(String[] args) {
		SpringApplication.from(TrabalhoApplication::main).with(TestTrabalhoApplication.class).run(args);
	}

}
