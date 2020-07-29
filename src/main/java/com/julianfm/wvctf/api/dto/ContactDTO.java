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
public class ContactDTO {
	
	@Size(min = 2, max = 100, message= "Contact name must be between 2 and 100 characters")
	private String name;
	
	@Size(min = 9, max = 9, message= "The phone number must be valid")
	private String phoneNumber;
	
	@Email(message = "Email should be valid")
	private String mail;
}
