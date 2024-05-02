package com.hotelService.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelService.entity.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, String>{
	
	public List<Hotel> findAll();
}
