package com.julianfm.wvctf.model.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long>{
	
	@Query("select * from order where key = :key;")
	Orders findByKey(@Param("key") String key);
	
}
