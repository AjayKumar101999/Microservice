package com.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exception.UserNotFoundException;
import com.userservice.external_services.HotelService;
import com.userservice.external_services.RatingService;
import com.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	UserRepository userRepository;
	RestTemplate restTemplate;
	HotelService hotelService;
	RatingService ratingService;

	public UserService(UserRepository userRepository, RestTemplate restTemplate,HotelService hotelService,RatingService ratingService) {
		this.userRepository = userRepository;
		this.restTemplate=restTemplate;
		this.hotelService=hotelService;
		this.ratingService=ratingService;
	}
	
	public User saveUser(User user) {
		String UId = UUID.randomUUID().toString();
		user.setUserId(UId);
		return userRepository.save(user);
	}

	public User findbyId(String userId) {
		//finding the user ratings by the rest template to call rating service
	 log.info("user service findbyid method is called");
	 User user = userRepository.findById(userId).orElseThrow(()->new  UserNotFoundException("User "+userId+" not found"));
	 log.info("user founded from db: " +user);
	 //Rating[] ratingsofUser = restTemplate.getForObject("http://RATINGSERVICE/rating/user/" + userId, Rating[].class);
	 List<Rating> ratings=ratingService.getRatings(userId);
	 //List<Rating> ratings = Arrays.stream(ratingsofUser).toList();
	 log.info("Ratings ="+ratings);
	 
	 List<Rating> ratinngs = ratings.stream().map(rating -> {
		log.info("rating.hotel id = "+ rating.getHotalId());
    	//ResponseEntity<Hotel> hoteldata=restTemplate.getForEntity("http://HOTELSERVICE/hotel/" + rating.getHotalId(), Hotel.class);
		rating.setHotel(hotelService.getHotel(rating.getHotalId()));
    	return rating;
	 }).collect(Collectors.toList());
	 log.info("Ratings after setting hotel ="+ratings);	 
	 user.setRatings(ratinngs);
	 log.info("user data = "+user);
	 return user;
	}
	
	public List<User> name() {
		return userRepository.findAll();
	}

	public List<User> findAllUser() {
		return userRepository.findAll();
		
	}
	
}
