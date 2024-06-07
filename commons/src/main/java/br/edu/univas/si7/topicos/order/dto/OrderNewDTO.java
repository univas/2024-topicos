package br.edu.univas.si7.topicos.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderNewDTO {
	private Long number;
	private String sellerName;
	private String sellerEmail;
	
	@NotNull(message = "Customer id can not be null.")
	private String customerId;
}
