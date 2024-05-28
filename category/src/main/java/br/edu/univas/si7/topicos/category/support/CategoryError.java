package br.edu.univas.si7.topicos.category.support;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryError {
	
	private String message;
	private Integer status;
	private Date date;

}
