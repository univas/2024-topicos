package br.edu.univas.si7.topicos.order.util;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import br.edu.univas.si7.topicos.order.entities.CustomerEntity;

public class CustomerEntityConverter {

	public static CustomerEntity toEntity(CustomerDTO dto) {
		return new CustomerEntity(dto.getDocumentNumber(), dto.getName(), dto.getEmail(), 
				dto.getPhoneNumber(), dto.getType().getType());
	}
	
	public static CustomerDTO toDTO(CustomerEntity entity) {
		return new CustomerDTO(entity.getDocumentNumber(), entity.getName(), 
				entity.getEmail(), entity.getPhoneNumber(),
				CustomerType.getType(entity.getType()));
	}
}
