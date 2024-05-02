package com.ratingService.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ratingService.entity.Rating;


public interface RatingRepository extends MongoRepository<Rating, String>{
	
	public List<Rating> findAll();
	public List<Rating> findByUserId(String userId);

}
