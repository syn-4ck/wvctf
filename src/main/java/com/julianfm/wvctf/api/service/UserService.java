package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.UserDTO;

@Service
public interface UserService {
	
	public UserDTO createOrUpdateUser(UserDTO userDTO);
	
	public void deleteUser (String username);

	public UserDTO findUserByUsername(String username);
	
	public List<UserDTO> findAllUsers();
}
