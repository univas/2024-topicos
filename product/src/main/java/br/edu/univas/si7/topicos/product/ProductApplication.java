package br.edu.univas.si7.topicos.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.product.entities.ProductEntity;
import br.edu.univas.si7.topicos.product.reposioties.ProductRepository;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProductEntity p1 = new ProductEntity(1, "Arroz", 7.30f, false);
		repo.save(p1);
		ProductEntity p2 = new ProductEntity(2, "Feijão", 2.45f, true);
		repo.save(p2);
		
		List<ProductEntity> produtos = repo.findAll();
		System.out.println(produtos);
	}
}
