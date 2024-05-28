package br.edu.univas.si7.topicos.category.support;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CategoryHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<CategoryError> handleObjectNotFound(
			CategoryNotFoundException ex, HttpServletRequest req) {
		CategoryError error = new CategoryError(
				ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date()
			);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CategoryException.class)
	public ResponseEntity<CategoryError> handleObjectNotFound(
			CategoryException ex, HttpServletRequest req) {
		CategoryError error = new CategoryError(
				ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date()
			);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
