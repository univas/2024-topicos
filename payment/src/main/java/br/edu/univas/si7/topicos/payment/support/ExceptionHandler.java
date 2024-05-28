package br.edu.univas.si7.topicos.payment.support;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> handleObjectNotFound(
			ObjectNotFoundException ex, HttpServletRequest req) {
		StandardError error = new StandardError(
				ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date()
			);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PaymentException.class)
	public ResponseEntity<StandardError> handleObjectNotFound(
			PaymentException ex, HttpServletRequest req) {
		StandardError error = new StandardError(
				ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date()
			);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}