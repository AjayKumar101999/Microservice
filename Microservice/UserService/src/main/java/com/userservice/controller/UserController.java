package com.userservice.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entity.User;
import com.userservice.response.Response;
import com.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	static int retry=1;
	
	UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService; 
	}
	
	@GetMapping("/json")
	public User json() {
		 return new User(); 
		
	}

	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.findAllUser();
		return ResponseEntity.ok(users);
	}
	
	
	@GetMapping("/{id}")
//	@CircuitBreaker(name = "UserToRatingAndHotel", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "UserToRatingAndHotelRetry", fallbackMethod = "ratingHotelFallbackRetry")
	@RateLimiter(name = "UserRateLimiter", fallbackMethod = "RateLimiter")
	public ResponseEntity<User> getUser(@PathVariable String id) {
	//	log.info("retry count is = "+ retry++);
		User user = userService.findbyId(id);
		return ResponseEntity.ok(user);
	}
	
//	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex) {
//		log.info("fallback is executed becuase of Rating service or hotel service is down"+ ex.getMessage());
//		User user = User.builder().userId("ghfg67").email("Ajay@gmail.com").name("Ajay kumar").about("Rating or otel service is down").build();
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
	
	
//	public ResponseEntity<User> ratingHotelFallbackRetry(String id, Exception ex) {
//		
//		log.info("fallback is executed becuase of Rating service or hotel service is down"+ ex.getMessage());
//	
//		User user = User.builder().userId("ghfg67").email("Ajay@gmail.com").name("Ajay kumar").about("Rating or otel service is down").build();
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
	
	
	public ResponseEntity<User> RateLimiter(String id, Exception ex) {
		
		log.info("fallback is executed becuase of Rating service or hotel service is down"+ ex.getMessage());
		User user = User.builder().userId("ghfg67").email("Ajay@gmail.com").name("Ajay kumar").about("Rating or otel service is down").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<>(new Response(201, "User saved succesfully"),HttpStatus.CREATED);
	}
	
	
}
