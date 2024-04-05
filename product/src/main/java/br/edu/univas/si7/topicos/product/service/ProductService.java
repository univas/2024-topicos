package br.edu.univas.si7.topicos.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.entities.ProductEntity;
import br.edu.univas.si7.topicos.product.reposioties.ProductRepository;
import br.edu.univas.si7.topicos.product.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.product.support.ProductException;
import br.edu.univas.si7.topicos.product.util.ProductEntityConverter;

@Service
public class ProductService {

	private ProductRepository repo;

	@Autowired
	private ProductEntityConverter converter;

	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public List<ProductDTO> findAll() {
		return repo.findAll().stream().map(ProductEntityConverter::toDTO) // usando lambda para converter os objetos
				.collect(Collectors.toList());
	}

	public ProductEntity findById(Integer code) {
		Optional<ProductEntity> obj = repo.findById(code);
		ProductEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + code));
		return entity;
	}

	public List<ProductDTO> findByActive(boolean b) {
		return repo.findByActive(true).stream().map(ProductEntityConverter::toDTO).collect(Collectors.toList());
	}

	public void createProduct(ProductDTO product) {
		repo.save(converter.toEntity(product));
	}

	public void updateProduct(ProductDTO product, Integer code) {
		if (code == null || product == null || !code.equals(product.getCode())) {
			throw new ProductException("Invalid product code.");
		}
		ProductEntity existingObj = findById(code);
		updateData(existingObj, product);
		repo.save(existingObj);
	}

	private void updateData(ProductEntity existingObj, ProductDTO newObj) {
		existingObj.setName(newObj.getName());
	}
}
