package com.julianfm.wvctf.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	
	private String username;
	
	private String email;
	
	private boolean flag_so;
	
	private boolean flag_di;
	
	private boolean flag_sf;
	
	private boolean flag_xss;
	
	private boolean flag_l;
}
