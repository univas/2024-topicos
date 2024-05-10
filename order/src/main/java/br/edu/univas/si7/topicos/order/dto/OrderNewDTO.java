package br.edu.univas.si7.topicos.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNewDTO {
	private Long number;
	private String sellerName;
	private String sellerEmail;
}
