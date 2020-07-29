package com.julianfm.wvctf.model.entity.mongo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "contact")
public class Contact {

	@Id
	private String id;
	
	private String name;
	
	private String phoneNumber;
	
	private String mail;
	
	
}
