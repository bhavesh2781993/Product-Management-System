package com.bz.pms.service;

import java.util.List;

import com.bz.pms.model.ProductVO;

public interface ProductService {	
	
	public List<ProductVO> fetchProducts();
	
	public ProductVO fetchProduct(String productName, String companyName);

	public ProductVO createProduct(ProductVO product);
	
	public void deleteProduct(ProductVO product);
	
	public ProductVO updateProduct(ProductVO product);
	
}
