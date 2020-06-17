package com.julianfm.wvctf.model.entity.mng;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(value = "manage_user")
public class ManageUser {
	
	@Id
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@Column(nullable=false)
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	private boolean flag_so;
	
	private boolean flag_di;
	
	private boolean flag_sf;
	
	private boolean flag_xss;
	
	private boolean flag_l;
	

}
