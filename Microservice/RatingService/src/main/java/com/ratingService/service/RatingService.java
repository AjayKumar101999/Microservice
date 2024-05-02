package com.ratingService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ratingService.entity.Rating;
import com.ratingService.exception.HotelNotFoundException;
import com.ratingService.repository.RatingRepository;

@Service
public class RatingService {
	
	RatingRepository ratingRepository;

	public RatingService(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	public Rating saveRating(Rating rating) {
		String UId = UUID.randomUUID().toString();
		rating.setRatingId(UId);
		return ratingRepository.save(rating);
	}

	public Rating findbyId(String ratingId) {
		return ratingRepository.findById(ratingId).orElseThrow(()->new  HotelNotFoundException("Rating "+ratingId+" not found"));
	}
	
	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	public List<Rating> findbyUserId(String userid) {
		return ratingRepository.findByUserId(userid);
	}
	
}
