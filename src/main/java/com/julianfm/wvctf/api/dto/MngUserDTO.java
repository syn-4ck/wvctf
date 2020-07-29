package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MngUserDTO {
	
	@Size(min = 3, max = 100, message= "Username must be between 3 and 20 characters")
	private String username;
	
	@Size(min = 8, max = 100, message= "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W\\_])[A-Za-z\\d\\W\\_]{8,}$")
	private String password;
	
	@Email(message = "Email should be valid")
	private String email;
	

}
