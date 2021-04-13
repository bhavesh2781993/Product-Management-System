package com.bz.pms.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bz.pms.document.Product;
import com.bz.pms.model.ProductVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductUtil {
	
	@Autowired
	private static ObjectMapper objectMapper;

	private ProductUtil() {}
	
	public List<Product> toProductEntityList(List<ProductVO> productVOList) {
		return objectMapper.convertValue(productVOList, new TypeReference<List<Product>>() {});
	}
	
	public Product toProductEntity(ProductVO productVO) {
		return objectMapper.convertValue(productVO, Product.class);
	}
	
	public List<ProductVO> toProductVoList(List<Product> productEntityList) {
		return objectMapper.convertValue(productEntityList, new TypeReference<List<ProductVO>>() {});
	}
	
	public ProductVO toProductVo(Product productEntity) {
		return objectMapper.convertValue(productEntity, ProductVO.class);
	}
	
}
