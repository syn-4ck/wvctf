package com.julianfm.wvctf.api.service.mng;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.FlagDTO;
import com.julianfm.wvctf.api.dto.MngUserDTO;
import com.julianfm.wvctf.model.entity.mng.UserFlag;

@Service
public interface MngUserService extends UserDetailsService{

	public MngUserDTO createOrUpdateMngUser (MngUserDTO mngUser);
	
	public UserFlag createFlag (String username, String flag) throws Exception;
	
	public List<FlagDTO> getFlagsOfUser (String username);
	
}
