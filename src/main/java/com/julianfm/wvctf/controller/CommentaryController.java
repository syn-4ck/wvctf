package com.julianfm.wvctf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julianfm.wvctf.api.dto.CommentaryDTO;
import com.julianfm.wvctf.api.service.CommentaryService;
import com.julianfm.wvctf.api.service.ProductService;



@RestController
@RequestMapping("/commentary")
public class CommentaryController {

	@Autowired
	CommentaryService commentaryService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("")
	public CommentaryDTO createOrUpdateUser(@Valid @RequestBody CommentaryDTO commentaryDTO) {
		return commentaryService.createOrUpdateCommentary(commentaryDTO);
	}
	
	@GetMapping("/{idProduct}")
	public List<CommentaryDTO> findByIdProduct(@PathVariable Long idProduct) {
		return commentaryService.findCommentaryByProduct(idProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteByUsername(@PathVariable Long id) {
		commentaryService.deleteCommentary(id);
		return ResponseEntity.ok(null);
	}
	
}
