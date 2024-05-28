package br.edu.univas.si7.topicos.category.dto;

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
public class CategoryDTO {
	
	private int id;
	private String name;
	private String family;
	private String classs;
	
	//@JsonIgnore
	private boolean active;

}
