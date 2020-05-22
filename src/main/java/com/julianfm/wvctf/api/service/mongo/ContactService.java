package com.julianfm.wvctf.api.service.mongo;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.ContactDTO;

@Service
public interface ContactService {
	
	public ContactDTO findByName(String username); 

}
