package br.edu.univas.si7.topicos.product.util;

import org.springframework.stereotype.Component;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.entities.ProductEntity;

@Component
public class ProductEntityConverter {
	
	public static ProductDTO toDTO(ProductEntity product) {
		return new ProductDTO(
				product.getCode(), product.getName(), 
				product.getPrice(), product.isActive());
	}
	
	public ProductEntity toEntity(ProductDTO prod) {
		System.out.println("toEntity: " + prod);
		return new ProductEntity(prod.getCode(), prod.getName(),
				prod.getPrice(), prod.isActive());
	}
}
