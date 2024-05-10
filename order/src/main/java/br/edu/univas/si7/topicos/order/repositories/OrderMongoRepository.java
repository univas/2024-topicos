package br.edu.univas.si7.topicos.order.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.univas.si7.topicos.order.entities.OrderEntity;

//@Repository
public interface OrderMongoRepository extends MongoRepository<OrderEntity, Integer> {

	Optional<OrderEntity> findByNumber(Long number);
}
