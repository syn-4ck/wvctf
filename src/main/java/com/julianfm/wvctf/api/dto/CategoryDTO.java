package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	
	@Size(min = 2, max = 100, message= "Category name must be between 2 and 100 characters")
	private String name;
	
}
