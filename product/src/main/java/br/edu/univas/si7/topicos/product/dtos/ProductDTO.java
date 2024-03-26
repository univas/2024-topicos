package br.edu.univas.si7.topicos.product.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {
	private Integer code;
	private String name;
	
	@JsonIgnore
	private float price;
	
	@JsonIgnore
	private boolean active;
}
