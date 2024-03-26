package br.edu.univas.si7.topicos.product.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.entities.ProductEntity;
import br.edu.univas.si7.topicos.product.reposioties.ProductRepository;
import br.edu.univas.si7.topicos.product.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.product.util.ProductEntityConverter;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository repo;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAllProducts() {
		return repo.findAll()
				.stream()
				.map(ProductEntityConverter::toDTO) //usando lambda para converter os objetos
				.collect(Collectors.toList());
	}

	@GetMapping("/{code}")
	public ProductDTO getProductById(@PathVariable Integer code) {
		Optional<ProductEntity> obj = repo.findById(code);
		ProductEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + code));
		return ProductEntityConverter.toDTO(entity);
	}

	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductEntity> getAllProductsActive() { // tarefa: retornar uma lista de ProductDTO
		return repo.findByActive(true);
	}

}
