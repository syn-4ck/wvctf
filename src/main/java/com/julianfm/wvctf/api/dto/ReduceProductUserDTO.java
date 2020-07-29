package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReduceProductUserDTO {
	
	@Size(min = 3, max = 100, message= "Username must be between 3 and 20 characters")
	private String username;

}
