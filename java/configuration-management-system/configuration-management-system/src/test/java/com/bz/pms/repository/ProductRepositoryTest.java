package com.bz.pms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.bz.pms.document.Product;

@DataMongoTest
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	private Product product;

	@BeforeEach
	public void setUp() {
		product = new Product();
		product.setProductName("dummy_product_1");
		product.setCompanyName("dummy_company_1");
		
		product = productRepository.save(product);
	}
	
	@Test
	void should_fetch_existing_product_by_name_and_company() {
		Optional<Product> expectedOptional = productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName());
		assertThat(expectedOptional).isNotEmpty();
	}
	
	@AfterEach
	public void tearDown() {
		productRepository.deleteById(product.getId());
	}

}
