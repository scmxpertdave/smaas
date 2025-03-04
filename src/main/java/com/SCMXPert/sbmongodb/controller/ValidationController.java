package com.SCMXPert.sbmongodb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;




@ControllerAdvice
public class ValidationController extends ResponseEntityExceptionHandler{
	
//	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handle(ConstraintViolationException constraintViolationException) {
	    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
	    String errorMessage = "";
	    if (!violations.isEmpty()) {
	        StringBuilder builder = new StringBuilder();
	        violations.forEach(violation -> builder.append(" " + violation.getMessage()));
	        errorMessage = builder.toString();
	    } else {
	        errorMessage = "ConstraintViolationException occured.";
	    }
	    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	 }
	
	
}
