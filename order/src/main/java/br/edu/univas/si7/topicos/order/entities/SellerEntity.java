package br.edu.univas.si7.topicos.order.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Sellers")
@NoArgsConstructor
@Getter
@Setter
public class SellerEntity {

	@Id
	private String id;
	private String name;
	private String email;

	public SellerEntity(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
}
