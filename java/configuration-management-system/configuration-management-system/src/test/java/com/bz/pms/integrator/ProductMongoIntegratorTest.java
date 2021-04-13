package com.bz.pms.integrator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bz.pms.document.Product;
import com.bz.pms.repository.ProductRepository;
import com.mongodb.MongoSocketException;

@ExtendWith(MockitoExtension.class)
class ProductMongoIntegratorTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductMongoIntegrator productMongoIntegrator;

	private Product product;
	
	@BeforeEach
	void setUp() {
		product = new Product();
		product.setProductName("dummy_product_1");
		product.setCompanyName("dummy_company_1");
	}
	
	@Test
	void fetchProducts_Should_Return_All_Products() {
		List<Product> productList = Arrays.asList(product);
		when(productRepository.findAll()).thenReturn(productList);
		
		List<Product> actual = productMongoIntegrator.fetchProducts();
		
		verify(productRepository).findAll();
		assertThat(actual).isEqualTo(productList);
	}
	
	@Test
	void fetchProducts_Should_Throw_MongoSocketException() {
		when(productRepository.findAll()).thenThrow(MongoSocketException.class);
		
		assertThrows(MongoSocketException.class, () -> {
			productMongoIntegrator.fetchProducts();
		});
	}
	
	@Test
	void fetchProductByProductAndComapnyName() {
		Optional<Product> optionalProduct = Optional.of(product);
		when(productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName())).thenReturn(optionalProduct);
		
		Product actual = productMongoIntegrator.fetchProductByProductAndComapnyName(product.getProductName(), product.getCompanyName());
		
		verify(productRepository).findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName());
		assertThat(actual).isEqualTo(product);
	}
	
	@Test
	void fetchProductByProductAndComapnyName_gives_null() {
		when(productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName())).thenReturn(Optional.ofNullable(null));
		
		Product actual = productMongoIntegrator.fetchProductByProductAndComapnyName(product.getProductName(), product.getCompanyName());
		
		verify(productRepository).findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName());
		assertThat(actual).isNull();
	}
	
	@Test
	void createProduct() {
		when(productRepository.save(product)).thenReturn(product);
		
		Product actual = productMongoIntegrator.createProduct(product);
		
		verify(productRepository).save(product);
		assertThat(actual).isEqualTo(product);
	}

	@Test
	void deleteProduct() {
		productMongoIntegrator.deleteProduct(product);
		
		verify(productRepository).delete(product);
	}

	@Test
	void updateProduct_should_update_product() {
		when(productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName())).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);
		
		Product actual = productMongoIntegrator.updateProduct(product);
		
		verify(productRepository).save(product);
		assertThat(actual).isEqualTo(product);
	}
	
	@Test
	void updateProduct_should_return_null() {
		when(productRepository.findByProductNameAndCompanyName(product.getProductName(), product.getCompanyName())).thenReturn(Optional.ofNullable(null));
		
		Product actual = productMongoIntegrator.updateProduct(product);
		
		assertThat(actual).isNull();
	}

}
