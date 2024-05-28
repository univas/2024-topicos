package br.edu.univas.si7.topicos.customer.entities;

import java.io.Serializable;

import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CustomerEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private long documentNumber;
	private String name;
	private String email;
	private String phoneNumber;
	private CustomerType type;
	public CustomerEntity(long documentNumber, String name, String email, String phoneNumber, CustomerType type) {
		super();
		this.documentNumber = documentNumber;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
}
