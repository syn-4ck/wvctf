package com.julianfm.wvctf.model.util.init;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.julianfm.wvctf.model.entity.mongo.Contact;
import com.julianfm.wvctf.model.repository.mongo.ContactRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitContactData {

	private final ContactRepository contactRepository;
	
	@PostConstruct
	void createData() {
		
		contactRepository.deleteAll();
		contactRepository.save(new Contact(null,"user1","123456789","user1@wvctf.com"));
		contactRepository.save(new Contact(null,"user2","987654321","user2@wvctf.com"));
		
	}
	
}
