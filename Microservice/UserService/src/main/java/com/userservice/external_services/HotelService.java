package com.userservice.external_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.entity.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
	
	@GetMapping("/hotel/{id}")
	public Hotel getHotel(@PathVariable String id);
}
