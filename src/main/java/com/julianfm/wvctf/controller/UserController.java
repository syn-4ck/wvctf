package com.julianfm.wvctf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.api.dto.UserDTO;
import com.julianfm.wvctf.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("")
	public UserDTO createOrUpdateUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.createOrUpdateUser(userDTO);
	}
	
	@GetMapping("/{username}")
	public UserDTO findByUsername(@PathVariable String username) {
		return userService.findUserByUsername(username);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
		userService.deleteUser(username);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("")
	public List<UserDTO> findAllUsers() {
		return userService.findAllUsers();
	}
	
	
}
