package com.julianfm.wvctf.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
	
	private String name;
	
	private String description;

	private String size;
	
	private String color;
	
	private double price;
	
	private CategoryDTO category;

	private ReduceProductUserDTO vendor;
	
}
