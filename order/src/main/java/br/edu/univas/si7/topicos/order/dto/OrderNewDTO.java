package br.edu.univas.si7.topicos.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNewDTO {
	private Long number;
	private String sellerName;
	private String sellerEmail;
	
	@NotNull(message = "Customer id can not be null.")
	private String customerId;
}
