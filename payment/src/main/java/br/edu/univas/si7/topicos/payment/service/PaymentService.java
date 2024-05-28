package br.edu.univas.si7.topicos.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.payment.dto.PaymentDTO;
import br.edu.univas.si7.topicos.payment.entities.PaymentEntity;
import br.edu.univas.si7.topicos.payment.enums.PaymentStatus;
import br.edu.univas.si7.topicos.payment.repositories.PaymentRepository;
import br.edu.univas.si7.topicos.payment.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.payment.support.PaymentException;
import br.edu.univas.si7.topicos.payment.util.PaymentEntityConverter;
import br.edu.univas.si7.topicos.payment.validator.PaymentValidator;

import java.util.Date;

@Service
public class PaymentService {
	
	private PaymentRepository repo;
	
	@Autowired
	private PaymentEntityConverter converter;
	
	@Autowired
	public PaymentService(PaymentRepository repo, PaymentEntityConverter converter) {
		this.repo = repo;
		this.converter = converter;
	}
	
	
	public List<PaymentDTO> findAll() {
	    return repo.findAllByActiveTrue() // Usa o novo método do repositório
	               .stream()
	               .map(PaymentEntityConverter::toDTO)
	               .collect(Collectors.toList());
	}

	
	public PaymentEntity findById(Integer id) {
	    Optional<PaymentEntity> obj = repo.findByIdAndActiveTrue(id); // Usa o novo método do repositório
	    PaymentEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
	    return entity;
	}

	
	public PaymentDTO createPayment(PaymentDTO payment) {
	    PaymentEntity savedEntity = repo.save(converter.toEntity(payment));
	    return converter.toDTO(savedEntity);
	}

	
	public void updatePayment(PaymentDTO payment, Integer id) {
		if (id == null || payment == null || !id.equals(payment.getId())) {
			throw new PaymentException("Invalid payment Id.");
		}
		PaymentEntity existingObj = findById(id);
		System.out.println(payment);
		updateData(existingObj, payment);
		repo.save(existingObj);
	}
	
	private void updateData(PaymentEntity existingObj, PaymentDTO newObj) {
	    existingObj.setMethod(newObj.getMethod() != null ? newObj.getMethod() : existingObj.getMethod());
	    existingObj.setDueDate(newObj.getDueDate() != null ? newObj.getDueDate() : existingObj.getDueDate());
	    existingObj.setAmount(newObj.getAmount() != 0 ? newObj.getAmount() : existingObj.getAmount());
	    existingObj.setTransactionId(newObj.getTransactionId() != 0 ? newObj.getTransactionId() : existingObj.getTransactionId());

	    // Atualiza o status e verifica se deve atualizar paidAt
	    existingObj.setStatus(newObj.getStatus() != null ? newObj.getStatus() : existingObj.getStatus());
	    existingObj.setPaidAt((newObj.getStatus() != null && newObj.getStatus() == PaymentStatus.PAID) ? new Date() : existingObj.getPaidAt());

	    // Atualiza o campo active somente se ele é especificamente definido no DTO
	    if (newObj.getActive() != null) {
	        existingObj.setActive(newObj.getActive());
	    }
	}


	public void deletePayment(Integer id) {
		if (id == null) {
			throw new PaymentException("Payment Id can not be null.");
		}
		PaymentEntity obj = findById(id);
		if (!PaymentValidator.validatePaymentStatusUpdateIsPaidOrCanceled(converter.toDTO(obj))) {
			throw new PaymentException("You cannot cancel a payment that has already been paid");
	    } else {
	    	try {
				//repo.delete(obj);
				obj.setActive(false);
				obj.setStatus(PaymentStatus.CANCELED);
				repo.save(obj);
				
			} catch (DataIntegrityViolationException e) {
				throw new PaymentException("Can not delete a Payment with dependencies constraints.");
			}
	    		
	    }
		
	}
	
}
