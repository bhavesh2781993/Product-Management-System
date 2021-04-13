package com.bz.pms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bz.pms.document.Product;
import com.bz.pms.integrator.ProductMongoIntegrator;
import com.bz.pms.model.ProductVO;
import com.bz.pms.util.ProductUtil;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductMongoIntegrator mongoIntegrator;
	
	@Mock
	private ProductUtil productUtil;
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	private ProductVO productVo;
	private Product product;
	
	@BeforeEach
	void setUp() {
		productVo = new ProductVO();
		productVo.setProductName("dummy_product_1");
		productVo.setCompanyName("dummy_company_1");
		
		product = new Product();
		product.setProductName("dummy_product_1");
		product.setCompanyName("dummy_company_1");
	}
	
	@Test
	void testFetchProducts() {
		List<Product> productList = Arrays.asList(product);
		List<ProductVO> productVoList = Arrays.asList(productVo);		
		
		when(mongoIntegrator.fetchProducts()).thenReturn(productList);
		when(productUtil.toProductVoList(productList)).thenReturn(productVoList);
		
		List<ProductVO> actual = productServiceImpl.fetchProducts();
		
		verify(mongoIntegrator).fetchProducts();
		verify(productUtil).toProductVoList(productList);

		assertThat(actual).isEqualTo(productVoList);
	}

	@Test
	@Disabled
	void testFetchProduct() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCreateProduct() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDeleteProduct() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testUpdateProduct() {
		fail("Not yet implemented");
	}

}
