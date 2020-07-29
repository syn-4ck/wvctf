package com.julianfm.wvctf.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	
	@Size(min = 3, max = 100, message= "Username must be between 3 and 20 characters")
	private String username;
	
	@Email(message = "Email should be valid")
	private String email;
	
	private boolean flag_so;
	
	private boolean flag_di;
	
	private boolean flag_sf;
	
	private boolean flag_xss;
	
	private boolean flag_l;
}
