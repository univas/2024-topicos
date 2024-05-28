package br.edu.univas.si7.topicos.customer.dto;

import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDTO {
	//private Integer id;

	@NotNull(message = "DocumentNumber can not be null.")
	private Long documentNumber;

	@NotNull(message = "Name can not be null.")
	@NotEmpty(message = "Name can not be empty")
	private String name;

	@NotNull(message = "Email can not be null.")
	@NotEmpty(message = "Email can not be empty")
	private String email;

	@NotNull(message = "PhoneNumber can not be null.")
	@NotEmpty(message = "PhoneNumber can not be empty")
	private String phoneNumber;

	@NotNull(message = "CustomerType can not be null.")
	private CustomerType type;
}
