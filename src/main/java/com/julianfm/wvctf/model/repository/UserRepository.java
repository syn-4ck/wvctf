package com.julianfm.wvctf.model.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	
	@Query("select * from users where username = :username;")
	Users findByUsername(@Param("username") String username);

}
