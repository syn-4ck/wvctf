package com.julianfm.wvctf.api.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlagDTO {

	@Size(min = 71, max = 73, message= "The flag must be valid")
	String flag;
	
	Date date;
	
	@Size(min = 1, max = 1000, message= "The flag description must be less than 1000 characters")
	String description;
	
}
