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
import br.edu.univas.si7.topicos.product.util.ProductEntityConverter;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<ProductDTO> findAll() {
		return repo.findAll()
				.stream()
				.map(ProductEntityConverter::toDTO) //usando lambda para converter os objetos
				.collect(Collectors.toList());
	}
	
	public ProductDTO findById(Integer code) {
		Optional<ProductEntity> obj = repo.findById(code);
		ProductEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + code));
		return ProductEntityConverter.toDTO(entity);
	}

	public List<ProductDTO> findByActive(boolean b) {
		return repo.findByActive(true)
				.stream()
				.map(ProductEntityConverter::toDTO)
				.collect(Collectors.toList());
	}
	
}
