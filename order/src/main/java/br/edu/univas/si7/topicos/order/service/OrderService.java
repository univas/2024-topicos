package br.edu.univas.si7.topicos.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.repositories.OrderMongoRepository;

@Service
public class OrderService {

	@Autowired
	private OrderMongoRepository repo;
	
	public List<OrderEntity> findAll() {
		return repo.findAll();
	}
}
