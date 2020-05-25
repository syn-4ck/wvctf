package com.julianfm.wvctf.api.service.mongo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.ContactDTO;

@Service
public interface ContactService {
	
	public List<ContactDTO> findByName(String username); 

}
