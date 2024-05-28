package br.edu.univas.si7.topicos.product.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductDTO {
	private Integer code;
	private String name;
	private float price;
	
	//@JsonIgnore
	private boolean active;
}
