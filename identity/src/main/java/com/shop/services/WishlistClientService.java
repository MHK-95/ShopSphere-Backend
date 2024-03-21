package com.shop.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.services.DTO.CartDTO;


@FeignClient(name = "wishlist-service", url = "localhost:8084/feign/wishlist")
public interface WishlistClientService {
	
	@PostMapping("/create/{userId}")
	public String createNewWishlist(@PathVariable("userId") int userId);
	

}
