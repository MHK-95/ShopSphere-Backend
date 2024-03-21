package com.shop.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.services.DTO.CartDTO;


@FeignClient(name = "cart-service", url = "localhost:8083/feign/cart")
public interface CartClientService {
	
	@PostMapping("/create/{userId}")
	public String createNewCart(@PathVariable("userId") int userId);
	

}
