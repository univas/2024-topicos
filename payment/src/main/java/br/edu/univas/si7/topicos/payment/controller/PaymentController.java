package br.edu.univas.si7.topicos.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.payment.dto.PaymentDTO;
import br.edu.univas.si7.topicos.payment.entities.PaymentEntity;
import br.edu.univas.si7.topicos.payment.enums.PaymentStatus;
import br.edu.univas.si7.topicos.payment.service.PaymentService;
import br.edu.univas.si7.topicos.payment.util.PaymentEntityConverter;
import br.edu.univas.si7.topicos.payment.validator.PaymentValidator;

import org.springframework.http.ResponseEntity;
import java.net.URI;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<PaymentDTO> getAllPayments() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public PaymentDTO getPaymentById(@PathVariable Integer id) {
		PaymentEntity entity = service.findById(id);
		return PaymentEntityConverter.toDTO(entity);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createPayment(@RequestBody PaymentDTO payment) {
		if (!PaymentValidator.validatePayment(payment)) {
            return ResponseEntity.badRequest().body("Invalid payment data");
        }
		payment.setPaidAt(null);
		payment.setActive(true);
		payment.setStatus(PaymentStatus.PENDING);
	    PaymentDTO savedPayment = service.createPayment(payment);
	    return ResponseEntity.created(URI.create("/api/payment/" + savedPayment.getId())).body(savedPayment);
	}

	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)  
	public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO dto, @PathVariable Integer id) {
	    if (!PaymentValidator.validatePaymentStatusUpdateCanceled(dto)) {
	        return ResponseEntity.badRequest().body("You must use a delete route to cancel a payment");
	    }
	    if (PaymentValidator.validatePaymentStatusUpdateIsPaidOrCanceled(dto)) {
	        return ResponseEntity.badRequest().body("Payment status is not pending and cannot be updated in this context.");
	    }
	    service.updatePayment(dto, id);
	    return ResponseEntity.noContent().build(); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePayment(@PathVariable Integer id) {
		service.deletePayment(id);
	}
	

}
