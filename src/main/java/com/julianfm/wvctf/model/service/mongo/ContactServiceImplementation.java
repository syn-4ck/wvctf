package com.julianfm.wvctf.model.service.mongo;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.ContactDTO;
import com.julianfm.wvctf.api.service.mongo.ContactService;
import com.julianfm.wvctf.model.entity.mongo.Contact;
import com.julianfm.wvctf.model.repository.mongo.ContactRepository;

@Service
public class ContactServiceImplementation implements ContactService{
	
	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public List<ContactDTO> findByName(String username) {
		
		ModelMapper contactMapper =  new ModelMapper();
		
		List<Contact> contacts = new ArrayList<Contact>();
		List<ContactDTO> findContactsDTO = new ArrayList<ContactDTO>();
		
		contacts = contactRepository.findByName(username);
		
		if (contacts.size()>0) {
			contacts.stream().forEach(c -> {
				findContactsDTO.add(contactMapper.map(c,ContactDTO.class));
			});
		}
		
		return findContactsDTO;
	}

}
