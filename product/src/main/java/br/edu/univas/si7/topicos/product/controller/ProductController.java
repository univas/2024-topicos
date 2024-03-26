package br.edu.univas.si7.topicos.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
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
		return service.findById(code);
	}

	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAllProductsActive() {
		return service.findByActive(true);
	}

}
