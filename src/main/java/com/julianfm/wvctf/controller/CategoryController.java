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

import com.julianfm.wvctf.api.dto.CategoryDTO;
import com.julianfm.wvctf.api.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("")
	public CategoryDTO createOrUpdateUser(@Valid @RequestBody CategoryDTO categoryDTO) {
		return categoryService.createOrUpdateCategory(categoryDTO);
	}
	
	@GetMapping("/{name}")
	public CategoryDTO findByUsername(@PathVariable String name) {
		return categoryService.findByName(name);
	}

	@DeleteMapping("/{name}")
	public ResponseEntity<Void> deleteByName(@PathVariable String name) {
		categoryService.deleteCategory(name);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("")
	public List<CategoryDTO> findAllUsers() {
		return categoryService.findAllCategories();
	}
	
	
}
