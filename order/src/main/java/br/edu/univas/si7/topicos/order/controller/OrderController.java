package br.edu.univas.si7.topicos.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.order.dto.OrderDTO;
import br.edu.univas.si7.topicos.order.dto.OrderNewDTO;
import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService service;

	// Exercício extra: criar os DTOs
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<OrderEntity> getAllOrders() {
		return service.findAll();
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody OrderNewDTO order) {
		service.createOrder(order);
	}

	@GetMapping("/{number}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long number) {
		OrderDTO dto = service.findByNumber(number);
		return ResponseEntity.ok().body(dto);
	}
}
