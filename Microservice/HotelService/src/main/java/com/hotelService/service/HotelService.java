package com.hotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotelService.entity.Hotel;
import com.hotelService.exception.HotelNotFoundException;
import com.hotelService.repository.HotelRepository;

@Service
public class HotelService {
	
	HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
	
	public Hotel saveHotel(Hotel hotel) {
		String UId = UUID.randomUUID().toString();
		hotel.setHotelId(UId);
		return hotelRepository.save(hotel);
	}

	public Hotel findbyId(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()->new  HotelNotFoundException("Hotel "+hotelId+" not found"));
	}
	
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}
	
}
