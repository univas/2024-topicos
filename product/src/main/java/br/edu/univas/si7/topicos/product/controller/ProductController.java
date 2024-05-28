package br.edu.univas.si7.topicos.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.entities.ProductEntity;
import br.edu.univas.si7.topicos.product.service.ProductService;
import br.edu.univas.si7.topicos.product.util.ProductEntityConverter;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAllProducts() {
		return service.findAll();
	}

	@GetMapping("/{code}")
	public ProductDTO getProductById(@PathVariable Integer code) {
		ProductEntity entity = service.findById(code);
		return ProductEntityConverter.toDTO(entity);
	}

	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAllProductsActive() {
		return service.findByActive(true);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductDTO product) {
		service.createProduct(product);
	}

	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody ProductDTO dto, @PathVariable Integer code) {
		service.updateProduct(dto, code);
	}

	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Integer code) {
		service.deleteProduct(code);
	}
}
