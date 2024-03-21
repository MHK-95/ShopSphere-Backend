package com.shop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.controllers.LoginDTO;
import com.shop.controllers.UserDTO;
import com.shop.model.UserEntity;
import com.shop.services.AdminService;
import com.shop.services.AuthenticationService;

@AutoConfigureTestDatabase(replace = Replace.NONE)
//@DataJpaTest
@SpringBootTest
class IdentityApplicationTests {
	
	private AuthenticationService authService = new AuthenticationService();
	
	private AdminService adminService = new AdminService();
	
	@Rollback(value = false)
	@Test
	@Order(1)
	public void registerUser() {
		UserDTO userDTO = UserDTO.builder()
				.username("test1")
				.email("test1@gmail.com")
				.password("1234")
				.role("USER").build();
		
		
		UserEntity userEntity = UserDTO.UserDTOToUserEntity(userDTO);
		UserDTO newUserDTO = authService.saveUser(userEntity);
		
		assertThat(newUserDTO.getId());
	}
	
	@Rollback(value = false)
	@Test
	@Order(2)
	public void getUsers() {
		assertThat(adminService.getAllUsers().stream().map(UserDTO::userEntityToUserDTO).collect(Collectors.toList()).size() > 0);
	}
	
	/*
	public UserDTO updateUser() {
		userDTO.setId(id);
		UserDTO newUserDTO = adminService.updateUser(userDTO, id);
		return newUserDTO;
	}
	
	public ResponseEntity deleteUser() {
		adminService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	*/
	
	
}
