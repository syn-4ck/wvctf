package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	
	@Size(min = 3, max = 100, message= "Username must be between 3 and 20 characters")
	private String username;
	
	private String password;

	@Size(min = 9, max = 9, message= "The phone number must be valid")
	private String phoneNumber;
	
	@Size(min = 3, max = 200, message= "Username must be between 3 and 200 characters")
	private String address;

}
