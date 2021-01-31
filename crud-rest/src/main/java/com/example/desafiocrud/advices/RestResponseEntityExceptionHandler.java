package com.example.desafiocrud.advices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.desafiocrud.exceptions.RecordNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private String BAD_REQUEST = "BAD_REQUEST";
      
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse> RecordNotFoundHandler(RecordNotFoundException ex) {
		 List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
