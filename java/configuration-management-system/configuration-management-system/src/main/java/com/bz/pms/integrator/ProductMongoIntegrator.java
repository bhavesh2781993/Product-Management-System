package com.bz.pms.integrator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.bz.pms.document.Product;
import com.bz.pms.exception.RecoveryFailureException;
import com.bz.pms.repository.ProductRepository;
import com.mongodb.MongoSocketException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductMongoIntegrator {

	@Autowired
	private ProductRepository productRepository;
	
	@Retryable(value = MongoSocketException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public List<Product> fetchProducts() {
		return productRepository.findAll();
	}

	@Retryable(value = MongoSocketException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public Product fetchProductByProductAndComapnyName(String productName, String companyName) {
		Optional<Product> productOptional = productRepository.findByProductNameAndCompanyName(productName, companyName);
		if(productOptional.isPresent()) {
			return productOptional.get();
		}
		return null;
	}
	
	@Retryable(value = MongoSocketException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Retryable(value = MongoSocketException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Retryable(value = MongoSocketException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public Product updateProduct(Product product) {
		Optional<Product> availableProduct = productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName());
		
		if(availableProduct.isPresent()) {
			return productRepository.save(product);
		}else {
			return null;
		}
	}
	
	@Recover
	Object recoverMongoException(MongoSocketException ex) {
		log.info("recover failed for exception: {}", ex);
		throw new RecoveryFailureException(ex.getMessage());
	}
	
	@Recover
	Object recoverException(Exception ex) {
		log.info("recover failed for exception: {}", ex);
		throw new RecoveryFailureException(ex.getMessage());
	}
	
}
