package br.edu.univas.si7.topicos.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<OrderEntity> getAllOrders() {
		return service.findAll();
	}
	
}
