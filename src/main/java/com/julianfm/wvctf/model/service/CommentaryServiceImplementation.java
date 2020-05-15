package com.julianfm.wvctf.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.CommentaryDTO;
import com.julianfm.wvctf.api.dto.ProductDTO;
import com.julianfm.wvctf.api.service.CommentaryService;
import com.julianfm.wvctf.model.entity.Commentary;
import com.julianfm.wvctf.model.entity.Product;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.exception.CommentaryNotFoundException;
import com.julianfm.wvctf.model.exception.DataNotFoundException;
import com.julianfm.wvctf.model.exception.UserNotFoundException;
import com.julianfm.wvctf.model.repository.CommentaryRepository;
import com.julianfm.wvctf.model.repository.ProductRepository;
import com.julianfm.wvctf.model.repository.UserRepository;

@Service
public class CommentaryServiceImplementation implements CommentaryService{

	@Autowired
	CommentaryRepository commentaryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public CommentaryDTO createOrUpdateCommentary(CommentaryDTO commentaryDTO) {
		ModelMapper commentaryMapper =  new ModelMapper();
		
		Commentary insertedCommentary = new Commentary();
		Commentary commentary = new Commentary();
		CommentaryDTO insertedCommentaryDTO = new CommentaryDTO();
		
		if(commentaryDTO!=null) {
			commentary = commentaryMapper.map(commentaryDTO, Commentary.class);
			Users user = userRepository.findByUsername(commentary.getUsers().getUsername());
			Product product = productRepository.findById(commentary.getProduct().getId()).get();
			if(user!=null && product!=null) {
				insertedCommentary = commentaryRepository.save(commentary);
				insertedCommentary.setUsers(user);
				insertedCommentary.setProduct(product);
				insertedCommentaryDTO = commentaryMapper.map(insertedCommentary, CommentaryDTO.class);
			} else {
				throw new DataNotFoundException();
			}
		}
		return insertedCommentaryDTO;
	}

	@Override
	public List<CommentaryDTO> findCommentaryByProduct(Long productId) {
		final List<CommentaryDTO> commentaryDTOList = new ArrayList<CommentaryDTO>();
		List<CommentaryDTO> commentaryDTOListResult = new ArrayList<CommentaryDTO>();
		ModelMapper commentaryMapper =  new ModelMapper();
		
		commentaryRepository.findAll().forEach(c -> commentaryDTOList.add(commentaryMapper.map(c,CommentaryDTO.class)));
		
		commentaryDTOListResult = commentaryDTOList.stream().filter(c -> c.getProduct().getId().equals(productId)).collect(Collectors.toList());
		
		return commentaryDTOListResult;
	}

	@Override
	public void deleteCommentary(Long id) {
		Commentary commentary = new Commentary();
		commentary = commentaryRepository.findById(id).get();
		
		if (commentary!=null) {
			commentaryRepository.delete(commentary);
		} else {
			throw new CommentaryNotFoundException();
		}
		
	}

}
