package com.julianfm.wvctf.model.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	@Query("select * from category where name = :name;")
	Category findByName(@Param("name") String name);
	
}
