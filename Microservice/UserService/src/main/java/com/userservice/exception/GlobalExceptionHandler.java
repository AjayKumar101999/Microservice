package com.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFound(UserNotFoundException ex) {
		return new ResponseEntity<>(new Response(204, ex.getMessage()), HttpStatus.NO_CONTENT);
		
	}

}
