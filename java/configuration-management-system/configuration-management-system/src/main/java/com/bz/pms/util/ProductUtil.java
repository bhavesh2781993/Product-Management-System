package com.bz.pms.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
		List<Product> convertedValue = null;
		if(!CollectionUtils.isEmpty(productVOList)) convertedValue = objectMapper.convertValue(productVOList, new TypeReference<List<Product>>() {});
		else convertedValue = new ArrayList<>();
		
		return convertedValue;
	}
	
	public Product toProductEntity(ProductVO productVO) {
		Product convertedValue = null;
		if(productVO != null) convertedValue = objectMapper.convertValue(productVO, Product.class);
		return convertedValue;
	}
	
	public List<ProductVO> toProductVoList(List<Product> productEntityList) {
		List<ProductVO> convertedValue = null;
		if(!CollectionUtils.isEmpty(productEntityList)) convertedValue = objectMapper.convertValue(productEntityList, new TypeReference<List<ProductVO>>() {});
		else convertedValue = new ArrayList<>();
		
		return convertedValue;
	}
	
	public ProductVO toProductVo(Product productEntity) {
		ProductVO convertedValue = null;
		if(productEntity != null) convertedValue = objectMapper.convertValue(productEntity, ProductVO.class);
		return convertedValue;
	}
	
}
