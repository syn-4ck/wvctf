package com.julianfm.wvctf.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
}
