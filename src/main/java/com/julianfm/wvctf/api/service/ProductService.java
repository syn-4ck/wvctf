package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.ProductDTO;

@Service
public interface ProductService {
	
	public ProductDTO createOrUpdateProduct(ProductDTO productDTO);
	
	public ProductDTO findById(Long id);
	
	public List<ProductDTO> findAllProducts();
	
	public void deleteProduct(Long id);

}
