package br.edu.univas.si7.topicos.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import br.edu.univas.si7.topicos.customer.entities.CustomerEntity;
import br.edu.univas.si7.topicos.customer.repositories.CustomerRepository;
import br.edu.univas.si7.topicos.customer.support.CustomerException;
import br.edu.univas.si7.topicos.customer.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.customer.util.CustomerEntityConverter;

@Service
public class CustomerService {

	private CustomerRepository repo;
	private CustomerEntityConverter converter;

	@Autowired
	public CustomerService(CustomerRepository repo, CustomerEntityConverter converter) {
		this.repo = repo;
		this.converter = converter;
	}

	public List<CustomerDTO> findAll() {
		return repo.findAll().stream().map(CustomerEntityConverter::toDTO)
				.collect(Collectors.toList());
	}

	public CustomerEntity findById(Integer id) {
		Optional<CustomerEntity> obj = repo.findById(id);
		CustomerEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
		return entity;
	}

	public void createCustomer(CustomerDTO customer) {
		if (customer.getDocumentNumber() == null) {
			throw new IllegalArgumentException("DocumentNumber cannot be null.");
		}
		if (customer.getName() == null || customer.getName().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty.");
		}
		if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
			throw new IllegalArgumentException("PhoneNumber cannot be null or empty.");
		}
		if (customer.getType() == null) {
			throw new IllegalArgumentException("CustomerType cannot be null.");
		}
		repo.save(converter.toEntity(customer));
	}

	public CustomerEntity updateCustomer(CustomerDTO customer, Integer id) {
		if (id == null || customer == null) {
			throw new CustomerException("Invalid customer id");
		}

		CustomerEntity existingObj = findById(id);
		updateData(existingObj, customer);
		repo.save(existingObj);
		return existingObj;
	}

	public void updateData(CustomerEntity existingObj, CustomerDTO newObj) {
		if (newObj.getEmail() != null) {
			existingObj.setEmail(newObj.getEmail());
		}
		if (newObj.getName() != null) {
			existingObj.setName(newObj.getName());
		}
		if (newObj.getPhoneNumber() != null) {
			existingObj.setPhoneNumber(newObj.getPhoneNumber());
		}
	}

	public void deleteCustomer(Integer id) {
		if (id == null) {
			throw new CustomerException("Customer id can not be null.");
		}
		CustomerEntity obj = findById(id);
		try {
			repo.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new CustomerException("Can not delete a Customer with dependencies constraints.");
		}
	}
}
