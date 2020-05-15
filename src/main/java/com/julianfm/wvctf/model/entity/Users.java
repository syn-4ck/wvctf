package com.julianfm.wvctf.model.entity;

import java.io.Serializable;

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
@Table(value = "users")
public class Users{
	
    @Id
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@Column(nullable=false)
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@Column(length=9, nullable=false)
	@NotBlank(message = "Phone number is mandatory")
	private String phoneNumber;
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	
}
