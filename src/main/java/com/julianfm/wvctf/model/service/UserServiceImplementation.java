package com.julianfm.wvctf.model.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.UserDTO;
import com.julianfm.wvctf.api.service.UserService;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.exception.UserNotFoundException;
import com.julianfm.wvctf.model.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO findUserByUsername (String username) {
		ModelMapper userMapper =  new ModelMapper();
		
		Users user = new Users();
		UserDTO findUserDTO = new UserDTO();
		
		user = userRepository.findByUsername(username);
		if (user!=null) {
			findUserDTO = userMapper.map(user,UserDTO.class);
		} else {
			throw new UserNotFoundException();
		}
		
		return findUserDTO;
	}

	@Override
	public UserDTO createOrUpdateUser(UserDTO userDTO) {
		
		ModelMapper userMapper =  new ModelMapper();
		
		Users insertedUser = new Users();
		Users user = new Users();
		UserDTO insertedUserDTO = new UserDTO();
		
		if(userDTO!=null) {
			user = userMapper.map(userDTO, Users.class);
			insertedUser = userRepository.save(user);
			insertedUserDTO = userMapper.map(insertedUser, UserDTO.class);
		}
		return insertedUserDTO;
	}

	@Override
	public void deleteUser(String username) {
		
		Users user = new Users();
		user = userRepository.findByUsername(username);
		
		if (user!=null) {
			userRepository.delete(user);
		} else {
			throw new UserNotFoundException();
		}
		
	}

	@Override
	public List<UserDTO> findAllUsers() {
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		ModelMapper userMapper =  new ModelMapper();
		
		userRepository.findAll().forEach(u -> userDTOList.add(userMapper.map(u, UserDTO.class)));
		
		return userDTOList;
	}
	
}
