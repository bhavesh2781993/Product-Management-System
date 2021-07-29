package com.bz.pms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bz.pms.document.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
