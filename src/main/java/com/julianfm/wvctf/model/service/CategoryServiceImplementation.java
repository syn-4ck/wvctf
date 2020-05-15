package com.julianfm.wvctf.model.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.CategoryDTO;
import com.julianfm.wvctf.api.service.CategoryService;
import com.julianfm.wvctf.model.entity.Category;
import com.julianfm.wvctf.model.exception.CategoryNotFoundException;
import com.julianfm.wvctf.model.repository.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO) {
		ModelMapper categoryMapper =  new ModelMapper();
		
		Category insertedCategory = new Category();
		Category category = new Category();
		CategoryDTO insertedCategoryDTO = new CategoryDTO();
		
		if(categoryDTO!=null) {
			category = categoryMapper.map(categoryDTO, Category.class);
			insertedCategory = categoryRepository.save(category);
			insertedCategoryDTO = categoryMapper.map(insertedCategory, CategoryDTO.class);
		}
		return insertedCategoryDTO;
	}

	@Override
	public CategoryDTO findByName(String name) {
		ModelMapper categoryMapper =  new ModelMapper();
		
		Category category = new Category();
		CategoryDTO findCategoryDTO = new CategoryDTO();
		
		category = categoryRepository.findByName(name);
		if (category!=null) {
			findCategoryDTO = categoryMapper.map(category,CategoryDTO.class);
		} else {
			throw new CategoryNotFoundException();
		}
		
		return findCategoryDTO;
	}

	@Override
	public List<CategoryDTO> findAllCategories() {
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		ModelMapper categoryMapper =  new ModelMapper();
		
		categoryRepository.findAll().forEach(u -> categoryDTOList.add(categoryMapper.map(u, CategoryDTO.class)));
		
		return categoryDTOList;
	}

	@Override
	public void deleteCategory(String name) {
		
		Category category = new Category();
		category = categoryRepository.findByName(name);
		
		if (category!=null) {
			categoryRepository.delete(category);
		} else {
			throw new CategoryNotFoundException();
		}
		
	}

}
