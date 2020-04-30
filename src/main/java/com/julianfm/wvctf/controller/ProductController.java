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

import com.julianfm.wvctf.api.dto.ProductDTO;
import com.julianfm.wvctf.api.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("")
	public ProductDTO createOrUpdateUser(@Valid @RequestBody ProductDTO productDTO) {
		return productService.createOrUpdateProduct(productDTO);
	}
	
	@GetMapping("/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return productService.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("")
	public List<ProductDTO> findAllUsers() {
		return productService.findAllProducts();
	}
	
	
}
