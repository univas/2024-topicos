package br.edu.univas.si7.topicos.order.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

	@Id private Long number;
	private Date dateRequest;
	
	@DBRef(lazy = true)
	private SellerEntity seller;
	
	private CustomerEntity customer;
}
