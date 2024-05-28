package br.edu.univas.si7.topicos.delivery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.deliveries.dto.DeliveriesDTO;
import br.edu.univas.si7.topicos.delivery.DeliveriesType;
import br.edu.univas.si7.topicos.delivery.entities.DeliveriesEntity;
import br.edu.univas.si7.topicos.delivery.service.DeliveriesService;
import br.edu.univas.si7.topicos.delivery.suporte.IdNotFoundException;
import br.edu.univas.si7.topicos.delivery.suporte.ObjectNotFoundException;
import br.edu.univas.si7.topicos.delivery.util.DeliveriesEntityConverter;

@RestController
@RequestMapping("/api/delivery")
public class DeliveriesController {
	
	@Autowired
	private DeliveriesService service;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<DeliveriesDTO> getAllDeliveries(){
		return service.findAll();
	}
	
	@GetMapping("/confirmation")
	@ResponseStatus(HttpStatus.OK)
	public List<DeliveriesDTO> getAllcustomerConfirmation(){
		return service.findByConfirmation(true);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDeliveries(@RequestBody DeliveriesDTO dto, @PathVariable Integer id) {
		service.updateDeliveries(dto, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDeliveriesById(@PathVariable Integer id) {
		try {
			DeliveriesEntity entity = service.findById(id);
			DeliveriesDTO dto = DeliveriesEntityConverter.toDTO(entity);
			return ResponseEntity.ok(dto);
		}catch (ObjectNotFoundException e) {
			IdNotFoundException error = new IdNotFoundException("ID not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createDeliveries(@RequestBody DeliveriesDTO deliveries) {
		service.createDeliveries(deliveries);
	}
	
	@GetMapping("/enum")
	public ResponseEntity<?> getEnumValues() {
	Map<String, Integer> enumValues = new HashMap<>();
	enumValues.put("FAST", DeliveriesType.FAST.ordinal());
	enumValues.put("WITHDRAW", DeliveriesType.WITHDRAW.ordinal());
	enumValues.put("BASIC", DeliveriesType.BASIC.ordinal());
	return ResponseEntity.ok(enumValues);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDeliveries(@PathVariable Integer id) {
		service.deleteDeliveries(id);
	}
	}



