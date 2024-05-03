package br.edu.univas.si7.topicos.order;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.repositories.OrderMongoRepository;

@SpringBootApplication
public class OrderApplication implements CommandLineRunner {

	@Autowired
	private OrderMongoRepository orderMongoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OrderEntity order1 = new OrderEntity("1", new Date());
		OrderEntity order2 = new OrderEntity("2", new Date());
		
		orderMongoRepo.save(order1);
		orderMongoRepo.save(order2);
	}
	
}
