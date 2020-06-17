package com.julianfm.wvctf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.api.dto.OrderDTO;
import com.julianfm.wvctf.api.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("")
	public ResponseEntity<String> createOrUpdateUser(@RequestParam String user, @RequestParam Long product) {
		
		boolean isCSRF = orderService.createOrUpdateOrder(user, product);
		
		if (isCSRF) {
			return ResponseEntity.ok("flag_sf_472fc248735f41d0f484f1942477304eb85a3bbbb361dc262bc3a3397ae35dca");
		} else {
			return ResponseEntity.ok("Created");
		}	
		
	}
	
	@GetMapping("/list")
	public List<OrderDTO> findById(@RequestParam String username) {
		return orderService.findOrderByUser(username);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok(null);
	}
	
}
