package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.CommentaryDTO;
import com.julianfm.wvctf.api.dto.ProductDTO;

@Service
public interface CommentaryService {

	public CommentaryDTO createOrUpdateCommentary(CommentaryDTO commentaryDTO);
	
	public List<CommentaryDTO> findCommentaryByProduct(Long idProduct);
	
	public void deleteCommentary(Long id);
	
}
