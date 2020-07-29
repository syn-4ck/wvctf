package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFlagDTO {
	
	@Size(min = 3, max = 100, message= "Username must be between 3 and 20 characters")
	String username;
	
	@Size(min = 71, max = 73, message= "The flag must be valid")
	String flag;

}
