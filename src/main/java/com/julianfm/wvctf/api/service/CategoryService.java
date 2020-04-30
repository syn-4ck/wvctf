package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.CategoryDTO;


@Service
public interface CategoryService {

	public CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO);
	
	public CategoryDTO findByName(String name);
	
	public List<CategoryDTO> findAllCategories();
	
	public void deleteCategory(String name);
	
}
