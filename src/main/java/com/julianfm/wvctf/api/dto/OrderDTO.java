package com.julianfm.wvctf.api.dto;

import java.util.Date;

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
	
	private UserDTO users;
	
	private ProductDTO product;
	
	private Date orderDate;
	
	private int count;
	
}
