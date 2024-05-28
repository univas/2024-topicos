package br.edu.univas.si7.topicos.order;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import br.edu.univas.si7.topicos.order.entities.CustomerEntity;
import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.entities.SellerEntity;
import br.edu.univas.si7.topicos.order.repositories.OrderMongoRepository;
import br.edu.univas.si7.topicos.order.repositories.SellerMongoRepository;

@SpringBootApplication
public class OrderApplication implements CommandLineRunner {

	@Autowired
	private OrderMongoRepository orderMongoRepo;
	
	@Autowired
	private SellerMongoRepository sellerRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SellerEntity s1 = new SellerEntity("Seller 01", "seller1@email.com");
		SellerEntity s2 = new SellerEntity("Seller 02", "seller2@email.com");
		SellerEntity s3 = new SellerEntity("Seller 03", "seller2@email.com");

		//limpa a coleção Sellers
		sellerRepo.deleteAll();
		
		//salva os vendedores
		sellerRepo.saveAll(Arrays.asList(s1, s2, s3));
		
		CustomerEntity ce1 = new CustomerEntity(1L, "c1", "e1@email", "111", CustomerType.PHISICPERSON.getType());
		CustomerEntity ce2 = new CustomerEntity(2L, "c2", "e2@email", "222", CustomerType.JURIDICPERSON.getType());
		OrderEntity order1 = new OrderEntity(1L, new Date(), s2, ce1);
		OrderEntity order2 = new OrderEntity(2L, new Date(), s3, ce1);
		
		//limpa a coleção de pedidos e salva novamente os pedidos
		orderMongoRepo.deleteAll();
//		orderMongoRepo.save(order1);
//		orderMongoRepo.save(order2);
	}
}
