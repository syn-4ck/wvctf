package com.julianfm.wvctf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.api.dto.ContactDTO;
import com.julianfm.wvctf.api.service.mongo.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	ContactService contactService;

	@GetMapping("/{name}")
	public ContactDTO findByUsername(@PathVariable String name) {
		return contactService.findByName(name);
	}
	
	
}
