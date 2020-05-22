package com.julianfm.wvctf.model.service.mongo;

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
	public ContactDTO findByName(String username) {
		
		ModelMapper contactMapper =  new ModelMapper();
		
		Contact contact = new Contact();
		ContactDTO findContactDTO = new ContactDTO();
		
		contact = contactRepository.findByName(username);
		
		if (contact!=null) {
			findContactDTO = contactMapper.map(contact,ContactDTO.class);
		}
		
		return findContactDTO;
	}

}
