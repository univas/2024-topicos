package br.edu.univas.si7.topicos.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.univas.si7.topicos.order.dto.OrderDTO;
import br.edu.univas.si7.topicos.order.dto.OrderNewDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderIntegrationTest {

	private ObjectMapper mapper = new ObjectMapper();
	private final String orderURL = "http://localhost:8080/api/order";

	@Test
	public void testGetOrderById() {
		int orderNumber = 1;
		try {
			Response resp = RestAssured.get(orderURL + "/" + orderNumber);
			assertEquals(HttpStatus.OK.value(), resp.getStatusCode());
			
			String jsonBody = resp.getBody().asString();
			OrderDTO order = mapper.readValue(jsonBody, OrderDTO.class);
			
			assertNotNull(order);
			assertEquals(1, order.getNumber());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			fail("Fail to get order with id: " + orderNumber, e);
		}
	}

	@Test
	public void testPostOrder() {
		OrderNewDTO order = new OrderNewDTO(3L, "Seller 3", "e@mail3.com", "1");
		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);
		assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());
	}

}
