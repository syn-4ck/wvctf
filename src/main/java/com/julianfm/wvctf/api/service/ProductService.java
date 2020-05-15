package com.julianfm.wvctf.api.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.julianfm.wvctf.api.dto.ProductDTO;

@Service
public interface ProductService {
	
	public ProductDTO createOrUpdateProduct(ProductDTO productDTO);
	
	public ProductDTO findById(Long id);
	
	public List<ProductDTO> findByName(String name) throws SQLException;
	
	public List<ProductDTO> findAllProducts();
	
	public void deleteProduct(Long id);

	public boolean uploadFile(MultipartFile file, String name);
	
	public boolean isCI (String name);

}
