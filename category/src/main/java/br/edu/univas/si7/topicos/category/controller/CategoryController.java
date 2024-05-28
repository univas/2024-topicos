package br.edu.univas.si7.topicos.category.controller;

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

import br.edu.univas.si7.topicos.category.dto.CategoryDTO;
import br.edu.univas.si7.topicos.category.entities.CategoryEntity;
import br.edu.univas.si7.topicos.category.service.CategoryService;
import br.edu.univas.si7.topicos.category.util.CategoryEntityConverter;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryDTO> getAllProducts() {
		return service.findAll();
	}

	@GetMapping("/{code}")
	public CategoryDTO getProductById(@PathVariable Integer code) {
		CategoryEntity entity = service.findById(code);
		return CategoryEntityConverter.toDTO(entity);
	}

	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryDTO> getAllProductsActive() {
		return service.findByActive(true);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody CategoryDTO product) {
		service.createProduct(product);
	}

	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody CategoryDTO dto, @PathVariable Integer code) {
		service.updateProduct(dto, code);
	}

	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Integer code) {
		service.deleteProduct(code);
	}

}
