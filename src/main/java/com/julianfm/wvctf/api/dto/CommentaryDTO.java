package com.julianfm.wvctf.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentaryDTO {

	@Valid
	public UserDTO users;
	
	@Valid
	public ProductDTO product;
	
	@Size(min = 1, max = 400, message= "Commentary text must be between 1 and 400 characters")
	public String text;
	
}
