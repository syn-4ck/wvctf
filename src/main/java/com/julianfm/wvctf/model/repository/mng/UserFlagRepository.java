package com.julianfm.wvctf.model.repository.mng;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.entity.mng.ManageUser;
import com.julianfm.wvctf.model.entity.mng.UserFlag;


public interface UserFlagRepository extends CrudRepository<UserFlag, Long>{

	@Query("select * from user_flag where users = :users;")
	List<UserFlag> findByUsers(@Param("user") ManageUser users);
	
}
