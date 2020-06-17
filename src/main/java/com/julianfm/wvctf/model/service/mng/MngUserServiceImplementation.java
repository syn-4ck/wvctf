package com.julianfm.wvctf.model.service.mng;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.FlagDTO;
import com.julianfm.wvctf.api.dto.MngUserDTO;
import com.julianfm.wvctf.api.dto.UserDTO;
import com.julianfm.wvctf.api.service.UserService;
import com.julianfm.wvctf.api.service.mng.MngUserService;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.entity.mng.ManageUser;
import com.julianfm.wvctf.model.entity.mng.UserFlag;
import com.julianfm.wvctf.model.entity.mng.UserPrincipal;
import com.julianfm.wvctf.model.repository.mng.ManageUserRepository;
import com.julianfm.wvctf.model.repository.mng.UserFlagRepository;
import com.julianfm.wvctf.model.util.Flags;

@Service
public class MngUserServiceImplementation implements MngUserService {
	
	@Autowired
	ManageUserRepository mngUserRepository;
	
	@Autowired
	UserFlagRepository userFlagRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	long TOT_DI = 2;
	long TOT_SO = 2;
	long TOT_SF = 1;
	long TOT_XSS = 2;
	long TOT_L = 1;
	
	@Override
	public MngUserDTO createOrUpdateMngUser (MngUserDTO mngUser) {
		ModelMapper userMapper =  new ModelMapper();
		
		ManageUser user = new ManageUser();
		ManageUser insertedUser = new ManageUser();
		MngUserDTO insertedUserDTO = new MngUserDTO();
		
		if (mngUser!=null) {
			user = userMapper.map(mngUser, ManageUser.class);
			user.setPassword(passwordEncoder.encode(mngUser.getPassword()));
			user.setFlag_di(false);
			user.setFlag_l(false);
			user.setFlag_sf(false);
			user.setFlag_so(false);
			user.setFlag_xss(false);
			insertedUser = mngUserRepository.save(user);
			insertedUserDTO = userMapper.map(insertedUser, MngUserDTO.class);
		}
		
		return insertedUserDTO;
	}
	
	@Override
	public List<FlagDTO> getFlagsOfUser (String username){
		
		ModelMapper userMapper =  new ModelMapper();
		
		List<UserFlag> flagList = new ArrayList<UserFlag>();
		List<FlagDTO> flags = new ArrayList<FlagDTO>();
		
		Comparator <UserFlag> myComparator = (arg1, arg2) 
                -> {
                    if(arg1.getDate().compareTo(arg2.getDate())<0) 
                       return 1;
                    else if (arg1.getDate().compareTo(arg2.getDate())>0)
                       return -1;
                    else
                       return 0;
                   };
		
        ManageUser u = mngUserRepository.findByUsername(username);
                   
		flagList = userFlagRepository.findByUsers(u).stream().sorted(myComparator).collect(Collectors.toList());
		
		flagList.forEach(f ->{
			
			String desc="";
			for (Flags fg : Flags.values()) {
		        if (fg.name().equals(f)) {
		             desc = fg.getDescription();
		        }
		    }
			
			FlagDTO flagDTO = new FlagDTO(f.getFlag(), f.getDate(),desc);
			
			flags.add(flagDTO);
		});
		
		return flags;
		
	}
	
	@Override
	public UserFlag createFlag (String username, String flag) throws Exception {
		
		boolean flagIsValid = false;
		
		for (Flags f : Flags.values()) {
	        if (f.name().equals(flag)) {
	            flagIsValid = true;
	        }
	    }
		
		UserFlag userFlag = null;
		
		if (flagIsValid) {
			ManageUser u = mngUserRepository.findByUsername(username);
			
			List<UserFlag> uf_list = userFlagRepository.findByUsers(u);
			
			long exists = uf_list.stream().filter(f -> f.getFlag().equals(flag)).count();
			
			if (exists==0) {
				String typeOfFlag = flag.split("_")[1];
				
				switch(typeOfFlag) {
				  case "di":
					if(uf_list.stream().filter(f -> f.getFlag().split("_")[1].equals(typeOfFlag)).count() == TOT_DI -1) {
				    	u.setFlag_di(true);
				    	mngUserRepository.save(u);
				    }
				    break;
				  case "so":
						if(uf_list.stream().filter(f -> f.getFlag().split("_")[1].equals(typeOfFlag)).count() == TOT_SO -1) {
					    	u.setFlag_so(true);
					    	mngUserRepository.save(u);
					    }
					    break;
				  case "sf":
						if(uf_list.stream().filter(f -> f.getFlag().split("_")[1].equals(typeOfFlag)).count() == TOT_SF -1) {
					    	u.setFlag_sf(true);
					    	mngUserRepository.save(u);
					    }
					    break;
				  case "xss":
						if(uf_list.stream().filter(f -> f.getFlag().split("_")[1].equals(typeOfFlag)).count() == TOT_XSS -1) {
					    	u.setFlag_xss(true);
					    	mngUserRepository.save(u);
					    }
					    break;
				  case "l":
						if(uf_list.stream().filter(f -> f.getFlag().split("_")[1].equals(typeOfFlag)).count() == TOT_L -1) {
					    	u.setFlag_l(true);
					    	mngUserRepository.save(u);
					    }
					    break;
				  default:
				    break;
				}

				
				userFlag = userFlagRepository.save(new UserFlag(null,u,flag,new Date()));
			} else {
				throw new Exception();
			}
			
		} else {
			throw new Exception();
		}
		
		return userFlag;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ManageUser user = this.mngUserRepository.findByUsername(username);
		
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
			
		return userPrincipal;
	}
}
