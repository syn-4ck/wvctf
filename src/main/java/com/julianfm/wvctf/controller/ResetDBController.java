package com.julianfm.wvctf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.model.util.init.ResetDB;

@RestController
@RequestMapping("/")
public class ResetDBController {

	@Autowired
	ResetDB reset;
	
	@GetMapping("/reset")
	public ResponseEntity<Void> deleteById() {
		reset.resetDBvalues();
		return ResponseEntity.ok(null);
	}
	
}
