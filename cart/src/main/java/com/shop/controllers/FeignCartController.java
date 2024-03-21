package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.services.CartService;

@RestController
@RequestMapping("/feign/cart")
public class FeignCartController {
	
	@Autowired
	CartService cartService;
	

	@PostMapping("/create/{userId}")
	public String createNewCart(@PathVariable("userId") int userId) {
		return String.valueOf(cartService.createUserCart(userId).getId());
	}
}
