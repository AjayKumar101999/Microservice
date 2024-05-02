package com.hotelService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelService.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<Response> userNotFound(HotelNotFoundException ex) {
		return new ResponseEntity<>(new Response(204, ex.getMessage()), HttpStatus.NO_CONTENT);
		
	}

}
