package br.edu.univas.si7.topicos.order.util;

import java.util.Date;

import br.edu.univas.si7.topicos.order.dto.OrderDTO;
import br.edu.univas.si7.topicos.order.dto.OrderNewDTO;
import br.edu.univas.si7.topicos.order.entities.OrderEntity;
import br.edu.univas.si7.topicos.order.entities.SellerEntity;

public class OrderEntityConverter {

	public static OrderEntity toEntity(OrderNewDTO dto) {
		SellerEntity seller = new SellerEntity(dto.getSellerName(), dto.getSellerEmail());
		OrderEntity entity = new OrderEntity(dto.getNumber(), new Date(), seller);
		return entity;
	}
	
	public static OrderDTO toDTO(OrderEntity entity) {
		return new OrderDTO(entity.getNumber(), entity.getSeller().getName());
	}
}
