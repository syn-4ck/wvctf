package com.julianfm.wvctf.model.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.ProductDTO;
import com.julianfm.wvctf.api.service.ProductService;
import com.julianfm.wvctf.model.entity.Product;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.exception.ProductNotFoundException;
import com.julianfm.wvctf.model.exception.UserNotFoundException;
import com.julianfm.wvctf.model.repository.ProductRepository;
import com.julianfm.wvctf.model.repository.UserRepository;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ProductDTO createOrUpdateProduct(ProductDTO productDTO) {
		
		ModelMapper productMapper =  new ModelMapper();
		
		Product insertedProduct = new Product();
		Product product = new Product();
		ProductDTO insertedProductDTO = new ProductDTO();
		
		if(productDTO!=null) {
			product = productMapper.map(productDTO, Product.class);
			Users user = userRepository.findByUsername(product.getVendor().getUsername());
			if(user!=null) {
				insertedProduct = productRepository.save(product);
				insertedProduct.setVendor(user);
				insertedProductDTO = productMapper.map(insertedProduct, ProductDTO.class);
			} else {
				throw new UserNotFoundException();
			}
		}
		return insertedProductDTO;
	}

	@Override
	public ProductDTO findById(Long id) {
		
		ModelMapper productMapper =  new ModelMapper();
		
		Product product = productRepository.findById(id).get();
		ProductDTO productDTO = new ProductDTO();
		
		if (product!=null) {
			productDTO = productMapper.map(product,ProductDTO.class);
		} else {
			throw new ProductNotFoundException();
		}
		
		return productDTO;
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = new Product();
		product = productRepository.findById(id).get();
		
		if (product!=null) {
			productRepository.delete(product);
		} else {
			throw new ProductNotFoundException();
		}
		
	}

	@Override
	public List<ProductDTO> findAllProducts() {
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		ModelMapper userMapper =  new ModelMapper();
		
		productRepository.findAll().forEach(p -> productDTOList.add(userMapper.map(p, ProductDTO.class)));
		
		return productDTOList;
	}

}
