package br.edu.univas.si7.topicos.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.order.dto.OrderDTO;
import br.edu.univas.si7.topicos.order.dto.OrderNewDTO;
import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.repositories.OrderMongoRepository;
import br.edu.univas.si7.topicos.order.repositories.SellerMongoRepository;
import br.edu.univas.si7.topicos.order.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.order.util.OrderEntityConverter;

@Service
public class OrderService {

	@Autowired
	private OrderMongoRepository repo;

	@Autowired
	private SellerMongoRepository sellerRepo;

	public List<OrderEntity> findAll() {
		return repo.findAll();
	}

	public void createOrder(OrderNewDTO dto) {
		OrderEntity entity = OrderEntityConverter.toEntity(dto);
		sellerRepo.save(entity.getSeller());
		repo.save(entity);
	}

	public OrderDTO findByNumber(Long number) {
		Optional<OrderEntity> obj = repo.findByNumber(number);
		return OrderEntityConverter.toDTO(obj.orElseThrow(
			() -> new ObjectNotFoundException("Order " + number + " not found")
		));
	}
}
