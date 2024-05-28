package br.edu.univas.si7.topicos.delivery;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.delivery.DeliveriesType;
import br.edu.univas.si7.topicos.delivery.entities.DeliveriesEntity;
import br.edu.univas.si7.topicos.delivery.repositories.DeliveriesRepository;

@SpringBootApplication
public class DeliveriesApplication implements CommandLineRunner {
	
	@Autowired
	private DeliveriesRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(DeliveriesApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		DeliveriesEntity d1 = new DeliveriesEntity();
		
		//d1.setDeliveryDateTime(new Date()); 
        d1.setCPFReceiver(12345678900L); 
        d1.setCustomerConfirmation(false); 
        d1.setAttempt(1); 
        d1.setDeliveriesType(DeliveriesType.FAST); 
        
		repo.save(d1);
		
		DeliveriesEntity d2 = new DeliveriesEntity();
		
		//d2.setDeliveryDateTime(new Date()); 
        d2.setCPFReceiver(13516847688L); 
        d2.setCustomerConfirmation(false); 
        d2.setAttempt(1); 
        d2.setDeliveriesType(DeliveriesType.BASIC); 
        
		repo.save(d2);
		
		List<DeliveriesEntity> deliveries = repo.findAll();
		System.out.println(deliveries);
		
	}
	
	

}
