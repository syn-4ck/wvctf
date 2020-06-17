package com.julianfm.wvctf.model.repository.mng;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.mng.ManageUser;


@Repository
public interface ManageUserRepository extends CrudRepository<ManageUser, Long> {
	
	@Query("select * from manage_user where username = :username;")
	ManageUser findByUsername(@Param("username") String username);

}
