package br.edu.univas.si7.topicos.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.customer.entities.CustomerEntity;
import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import br.edu.univas.si7.topicos.customer.repositories.CustomerRepository;

@SpringBootApplication
public class CustomerApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		CustomerEntity c1 = new CustomerEntity(11111111111L, "c1", "e1@email", "111", CustomerType.PHISICPERSON);
		repo.save(c1);
		List<CustomerEntity> customer = repo.findAll();
	}
}
