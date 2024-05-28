package br.edu.univas.si7.topicos.order.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Service
public class OrderClient {

	private ObjectMapper mapper = new ObjectMapper();
	private final String customerURL = "http://localhost:8085/api/customer";

	public CustomerDTO getCustomerDetail(String customerId) {
		try {
			Response resp = RestAssured.get(customerURL + "/" + customerId);
			String jsonBody = resp.getBody().asString();
			CustomerDTO details = mapper.readValue(jsonBody, CustomerDTO.class);
			return details;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Fail to get customer with id: " + customerId, e);
		}
	}
}
