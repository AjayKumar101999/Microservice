package com.ratingService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("user_ratings")
@Data
public class Rating {
	
	@Id
	private String ratingId;
	private String userId;
	private String hotalId;
	private double stars;
	private String feedback;
	private Hotel hotal;

}
