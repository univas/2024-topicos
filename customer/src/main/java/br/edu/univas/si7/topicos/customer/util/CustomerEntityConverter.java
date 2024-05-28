package br.edu.univas.si7.topicos.customer.util;

import org.springframework.stereotype.Component;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import br.edu.univas.si7.topicos.customer.entities.CustomerEntity;

@Component
public class CustomerEntityConverter {
	public static CustomerDTO toDTO(CustomerEntity customer) {
		return new CustomerDTO(
				customer.getDocumentNumber(), customer.getName(),
				customer.getEmail(), customer.getPhoneNumber(), customer.getType());
	}

	public CustomerEntity toEntity(CustomerDTO customer) {
		return new CustomerEntity(
				customer.getDocumentNumber(), customer.getName(),
				customer.getEmail(), customer.getPhoneNumber(), customer.getType());
	}
}
