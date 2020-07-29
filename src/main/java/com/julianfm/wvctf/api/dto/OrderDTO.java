package com.julianfm.wvctf.api.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.julianfm.wvctf.model.entity.Product;
import com.julianfm.wvctf.model.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
    private Long id;
	
    @Valid
	private UserDTO users;
	
    @Valid
	private ProductDTO product;
	
	private Date orderDate;
	
    @Max(value = 100, message = "A order cannot have more than 100 products")
	private int count;
	
}
