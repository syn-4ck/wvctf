package com.julianfm.wvctf.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
    @Size(min = 5, max = 100, message= "Product name must be between 5 and 70 characters")
	private String name;
    
    @Size(min = 0, max = 400, message= "Product name must be between 0 and 400 characters")
	private String description;

    @Size(min = 1, max = 5, message= "Size must be between 1 and 5 alphanumeric characters")
	private String size;
	
    @Size(min = 1, max = 25, message= "Color must be between 1 and 25 characters")
	private String color;
	
    @NotNull
	private double price;
	
    @Valid
	private CategoryDTO category;
    
    @Valid
	private ReduceProductUserDTO vendor;
	
}
