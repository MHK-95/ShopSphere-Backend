package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.services.WishlistService;

@RestController
@RequestMapping("/feign/wishlist")
public class FeignWishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@PostMapping("/create/{userId}")
	public String createNewWishlist(@PathVariable("userId") int userId) {
		return String.valueOf(wishlistService.createWishlist(userId).getId());
	}

}
