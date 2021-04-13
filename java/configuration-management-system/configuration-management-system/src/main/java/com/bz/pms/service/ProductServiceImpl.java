package com.bz.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bz.pms.document.Product;
import com.bz.pms.exception.CustomTechnicalException;
import com.bz.pms.integrator.ProductMongoIntegrator;
import com.bz.pms.model.ProductVO;
import com.bz.pms.util.ProductUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMongoIntegrator mongoIntegrator;
	
	@Autowired
	private ProductUtil productUtil;

	@Override
	public List<ProductVO> fetchProducts() {
		try {
			List<Product> products = mongoIntegrator.fetchProducts();
			
			log.info("total fetched products: {}", products.size());
			
			return productUtil.toProductVoList(products);			
		} catch (Exception e) {
			log.error("Error fetching products: {}", e);
			throw new CustomTechnicalException(e.getMessage());
		}
	}

	@Override
	public ProductVO fetchProduct(ProductVO product) {
		
		return null;
	}

	@Override
	public ProductVO createProduct(ProductVO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO deleteProduct(ProductVO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO updateProduct(ProductVO product) {
		// TODO Auto-generated method stub
		return null;
	}

}
