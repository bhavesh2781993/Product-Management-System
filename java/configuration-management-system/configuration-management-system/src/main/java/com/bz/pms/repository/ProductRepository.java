package com.bz.pms.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bz.pms.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> findByProductNameAndCompanyName(String productName, String companyName);
	
}
