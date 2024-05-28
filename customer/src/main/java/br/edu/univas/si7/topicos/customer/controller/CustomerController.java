package br.edu.univas.si7.topicos.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import br.edu.univas.si7.topicos.customer.entities.CustomerEntity;
import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import br.edu.univas.si7.topicos.customer.service.CustomerService;
import br.edu.univas.si7.topicos.customer.util.CustomerEntityConverter;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDTO> getAllCustomers() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable Integer id) {
		CustomerEntity entity = service.findById(id);
		return CustomerEntityConverter.toDTO(entity);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(@RequestBody CustomerDTO customer) {
		CustomerDTO data = new CustomerDTO();
		String characters = String.valueOf(customer.getDocumentNumber());
		System.out.println(characters);
		if (characters.length() == 11) {
			data.setDocumentNumber(customer.getDocumentNumber());
			data.setName(customer.getName());
			data.setEmail(customer.getEmail());
			data.setPhoneNumber(customer.getPhoneNumber());
			data.setType(CustomerType.PHISICPERSON);
			service.createCustomer(data);
		} else if (characters.length() == 14) {
			data.setDocumentNumber(customer.getDocumentNumber());
			data.setName(customer.getName());
			data.setEmail(customer.getEmail());
			data.setPhoneNumber(customer.getPhoneNumber());
			data.setType(CustomerType.JURIDICPERSON);
			service.createCustomer(data);
		} else {
			throw new IllegalArgumentException("Invalid document number");
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO dto, @PathVariable Integer id) {
		CustomerEntity entity = service.updateCustomer(dto, id);
		return CustomerEntityConverter.toDTO(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id) {
		service.deleteCustomer(id);
	}
}
