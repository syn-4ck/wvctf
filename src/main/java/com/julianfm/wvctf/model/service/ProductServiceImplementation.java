package com.julianfm.wvctf.model.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public boolean isCI (String name) {
		String pathname = Paths.get("").toAbsolutePath().toString()+"/logs/log.txt";
		Date date = new Date();
		String command = "cmd /c echo Create_"+name+"_"+date.toString()+" >> "+pathname;
		try {
			String line;
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader input =  new BufferedReader (new InputStreamReader(p.getInputStream()));  
			line = input.lines().collect(Collectors.joining()); 
			input.close();
			if (line.length()>0) {
				return true;
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return false;
	}
	
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
		ModelMapper productMapper =  new ModelMapper();
		
		productRepository.findAll().forEach(p -> productDTOList.add(productMapper.map(p, ProductDTO.class)));
		
		return productDTOList;
	}

	@Override
	public List<ProductDTO> findByName(String name) throws SQLException {
		ModelMapper productMapper =  new ModelMapper();
		
		List<Product> products = productRepository.findByName(name);
		List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();
		
		products.forEach(p -> {
			ProductDTO pDTO = new ProductDTO();
			pDTO = productMapper.map(p, ProductDTO.class);
			pDTO.setCategory(this.findById(p.getId()).getCategory());
			pDTO.setVendor(this.findById(p.getId()).getVendor());
			productsDTO.add(pDTO);
		});
		
		return productsDTO;
	}

	@Override
	public boolean uploadFile(MultipartFile file, String name) {
		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}

		String pathname = Paths.get("").toAbsolutePath().toString()+"/img/";
		
		String url = pathname+name;
	    File fileToSave = new File(url);
	    try {
		    fileToSave.createNewFile();
		    FileOutputStream fos = new FileOutputStream(fileToSave); 
		    fos.write(file.getBytes());
		    fos.close();
	    } catch (Exception e) {
	    	throw new RuntimeException("The file cannot be uploaded");
	    }
	    
	    String urlSanitize = url.replace("../", "");
	    File fileExists = new File(urlSanitize);
	    boolean exists = fileExists.exists();
	    if (!exists) {
	    	return true;
	    } else {
	    	return false;
	    }
		
	}

	@Override
	public List<ProductDTO> findByVendor(String name) {
		ModelMapper productMapper =  new ModelMapper();
		
		List<Product> products = new ArrayList<Product>();
		
		productRepository.findAll().forEach(p -> {
			if(p.getVendor().getUsername().equals(name)) {
				products.add(p);
			}
		});
		List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();
		
		products.forEach(p -> {
			ProductDTO pDTO = new ProductDTO();
			pDTO = productMapper.map(p, ProductDTO.class);
			pDTO.setCategory(this.findById(p.getId()).getCategory());
			pDTO.setVendor(this.findById(p.getId()).getVendor());
			productsDTO.add(pDTO);
		});
		
		return productsDTO;
	}

}
