package com.hotelService.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelService.entity.Hotel;
import com.hotelService.response.Response;
import com.hotelService.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	HotelService hotelService;

	public HotelController(HotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}
	
	@GetMapping("/json")
	public Hotel json() {
		return new Hotel();
		
	}

	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> gethotel(@PathVariable String id) {
		Hotel hotel = hotelService.findbyId(id);
		return ResponseEntity.ok(hotel);
	}
	
	@GetMapping()
	public ResponseEntity<List<Hotel>> getAllHotel() {
		List<Hotel> hotels = hotelService.getAllHotels();
		return ResponseEntity.ok(hotels);
	}
	
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody Hotel hotel) {
		hotelService.saveHotel(hotel);
		return new ResponseEntity<>(new Response(201, "Hotel saved succesfully"),HttpStatus.CREATED);
	}
	
	
}
