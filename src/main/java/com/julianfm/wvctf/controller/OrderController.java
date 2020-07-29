package com.julianfm.wvctf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.api.dto.OrderDTO;
import com.julianfm.wvctf.api.service.OrderService;

@RestController
@RequestMapping("")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseEntity<OrderDTO> createOrUpdateUser(@RequestHeader(value="Sec-Fetch-Mode") String browser, @RequestParam String user, @RequestParam Long product) {

		orderService.createOrUpdateOrder(browser, user, product);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@GetMapping("/orders/list")
	public List<OrderDTO> findById(@RequestParam String username) {
		return orderService.findOrderByUser(username);
	}
	
	@GetMapping("/sales")
	public List<OrderDTO> findByVendor(@RequestParam String username) {
		return orderService.findOrderByVendor(username);
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/orders/check")
	public List<OrderDTO> orderInUser2 () {
		return orderService.orderInUser2();
	}
	
}
