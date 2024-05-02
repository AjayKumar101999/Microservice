package com.ratingService.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingService.entity.Rating;
import com.ratingService.response.Response;
import com.ratingService.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	RatingService ratingService;

	public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}
	
	@GetMapping("/json")
	public Rating json() {
		return new Rating();
		
	}

	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getrating(@PathVariable String id) {
		Rating rating = ratingService.findbyId(id);
		return ResponseEntity.ok(rating);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<Rating>> getratingbyUserId(@PathVariable String userid) {
		List<Rating> rating = ratingService.findbyUserId(userid);
		return ResponseEntity.ok(rating);
	}
	
	@GetMapping()
	public ResponseEntity<List<Rating>> getAllRating() {
		List<Rating> ratings = ratingService.getAllRatings();
		return ResponseEntity.ok(ratings);
	}
	
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody Rating rating) {
		ratingService.saveRating(rating);
		return new ResponseEntity<>(new Response(201, "Rating saved succesfully"),HttpStatus.CREATED);
	}
}
