package com.julianfm.wvctf.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Commentary;

@Repository
public interface CommentaryRepository extends CrudRepository<Commentary, Long> {

	
}
