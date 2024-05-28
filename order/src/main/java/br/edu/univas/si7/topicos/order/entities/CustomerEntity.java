package br.edu.univas.si7.topicos.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerEntity {
	private Long documentNumber;
	private String name;
	private String email;
	private String phoneNumber;
	private Integer type;
}
