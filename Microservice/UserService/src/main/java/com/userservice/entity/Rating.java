package com.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	private Hotel hotel;
	private String ratingId;
	private String userId;
	private String hotalId;
	private int stars;
	private String  feedback;
	
}
