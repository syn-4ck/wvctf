package com.julianfm.wvctf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.julianfm.wvctf.api.dto.CategoryDTO;
import com.julianfm.wvctf.api.dto.FlagDTO;
import com.julianfm.wvctf.api.dto.MngUserDTO;
import com.julianfm.wvctf.api.dto.ProductDTO;
import com.julianfm.wvctf.api.dto.UserDTO;
import com.julianfm.wvctf.api.dto.UserFlagDTO;
import com.julianfm.wvctf.api.dto.UserLogin;
import com.julianfm.wvctf.api.service.ProductService;
import com.julianfm.wvctf.api.service.UserService;
import com.julianfm.wvctf.api.service.mng.MngUserService;
import com.julianfm.wvctf.model.entity.mng.ManageUser;
import com.julianfm.wvctf.model.entity.mng.UserFlag;
import com.julianfm.wvctf.model.repository.mng.ManageUserRepository;

@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MngUserService mngUserService;
	
	@Autowired
	ManageUserRepository mgrUserRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/products")
	public ResponseEntity<ProductDTO> createOrUpdateUser(@Valid @RequestBody ProductDTO productDTO) {
		boolean isCI = productService.isCI(productDTO.getName());
		ProductDTO p = productService.createOrUpdateProduct(productDTO);
		
		if (isCI) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("flag","flag_so_8d80dd6e4c4449c0fdbcce2f919a82654b169c5f7351193ccd24d725147fab53");
			return new ResponseEntity<ProductDTO>(p, headers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
	}
	
	@GetMapping("/products/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("/products/search")
	public List<ProductDTO> findByName() {
		try {
			return productService.findByName("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<ProductDTO>();
	}
	
	@GetMapping("/products/vendor/{name}")
	public List<ProductDTO> findByVendor(@PathVariable String name) {
		return productService.findByVendor(name);
	}
	
	@GetMapping("/products/search/{name}")
	public List<ProductDTO> findByName(@PathVariable String name) {
		try {
			return productService.findByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<ProductDTO>();
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/products")
	public List<ProductDTO> findAllUsers() {
		return productService.findAllProducts();
	}
	
	@PostMapping(value = "/upload", headers = "Content-Type=multipart/form-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
		boolean pt = productService.uploadFile(file,name);
		if (pt) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Set-Cookie","flag=flag_so_a936c7beb36c21bc4e160c0771e296abb4777d0be6603ce739e0e0d494b2e318; Max-Age=600000");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
		} else {
			return ResponseEntity.ok(null);
		}
	}
	
	@GetMapping("/image/{name}")
	public ResponseEntity<InputStreamResource> getFile(@PathVariable String name) throws IOException {
		
		String pathname = Paths.get("").toAbsolutePath().toString()+"/img/"+name;

		File file = new File(pathname);
	    InputStream targetStream = new FileInputStream(file);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(targetStream));
	}
	
	@GetMapping("/credentials")
	public UserLogin getUser (@RequestParam("username") String username, @RequestParam("password") String password) {
		
		ModelMapper mgrUserMapper = new ModelMapper();
		ManageUser mgrUser = mgrUserRepository.findByUsername(username);
		
		if (mgrUser!=null) {
			if (passwordEncoder.matches(password, mgrUser.getPassword())) {
				return mgrUserMapper.map(mgrUser,UserLogin.class);
			} else {
				throw new UsernameNotFoundException("");
			}
		} else {
			throw new UsernameNotFoundException("");
		}
		
	}
	
	@PostMapping("/mgruser")
	public MngUserDTO createOrUpdateUser(@Valid @RequestBody MngUserDTO userDTO) {
		return mngUserService.createOrUpdateMngUser(userDTO);
	}
	
	@GetMapping("/mgruser/{username}")
	public UserLogin getUser(@PathVariable String username) {
		ModelMapper mgrUserMapper = new ModelMapper();
		ManageUser user = mgrUserRepository.findByUsername(username);
		if (user!=null) {
		return mgrUserMapper.map(user,UserLogin.class);
		} else {
			return null;
		}
	}
	
	@PostMapping("/flag")
	public UserFlag createUserFlag(@Valid @RequestBody UserFlagDTO uf) throws Exception {
		return mngUserService.createFlag(uf.getUsername(), uf.getFlag());
	}
	
	@GetMapping("/flags/{username}")
	public List<FlagDTO> getFlagsUser(@PathVariable String username){
		return mngUserService.getFlagsOfUser(username);
	}
	
}
