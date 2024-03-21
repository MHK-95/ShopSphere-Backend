package com.shop.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Role;
import com.shop.model.UserEntity;
import com.shop.services.AdminService;
import com.shop.services.AuthenticationService;
import com.shop.services.CartClientService;
import com.shop.services.WishlistClientService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	WishlistClientService wishlistClientService;
	
	@Autowired
	CartClientService cartClientService;
	
	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody UserDTO userDTO) {
		userDTO.setRole("User");
		UserEntity userEntity = UserDTO.UserDTOToUserEntity(userDTO);
		UserDTO newUserDTO = authenticationService.saveUser(userEntity);
		
		// When registering a new user create for them a new cart and wishlist
		cartClientService.createNewCart(newUserDTO.getId());
		wishlistClientService.createNewWishlist(newUserDTO.getId());
		return newUserDTO;
	}
	
	@PostMapping("/register/admin")
	public UserDTO registerAdmin(@RequestBody UserDTO userDTO) {
		userDTO.setRole("ADMIN");
		UserEntity userEntity = UserDTO.UserDTOToUserEntity(userDTO);
		
		return authenticationService.saveAdmin(userEntity);
	}
	
	@PostMapping("/login")
	public UserDTO loginUser(@RequestBody LoginDTO loginDTO) {
		return authenticationService.loginUser(loginDTO);
	}
	
	@PostMapping("/validate-jwt")
	public String validateJWT(@RequestParam("jwt") String jwt) {
		return String.valueOf(authenticationService.validateJWT(jwt));
	}
	
	@PostMapping("/validate-jwt-userid")
	public String validateJWTAndUserId(@RequestParam("jwt") String jwt, @RequestParam("userid") int userid) {
		return String.valueOf(authenticationService.validateJWTAndUserId(jwt, userid));
	}
	
	@PostMapping("/validate-jwt-admin")
	public String validateJWTAndIsAdmin(@RequestParam("jwt") String jwt) {
		return String.valueOf(authenticationService.validateJWTAndIsAdmin(jwt));
	}
	
	@Autowired
	AdminService adminService;
	
	
	@GetMapping("/admin/users")
	public List<UserDTO> getUsers() {
		return adminService.getAllUsers().stream().map(UserDTO::userEntityToUserDTO).collect(Collectors.toList());
	}
	
	@PutMapping("/admin/users/{id}")
	public UserDTO updateUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
		UserDTO newUserDTO = adminService.updateUser(userDTO, id);
		return newUserDTO;
	}
	
	@DeleteMapping("/admin/users/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		adminService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	

}
