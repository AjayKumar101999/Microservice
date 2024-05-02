package com.userservice.external_services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.entity.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
	
	@GetMapping("/rating/user/{userid}")
	List<Rating> getRatings(@PathVariable String userid);
}
