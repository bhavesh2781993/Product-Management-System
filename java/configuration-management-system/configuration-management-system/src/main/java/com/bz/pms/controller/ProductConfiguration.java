package com.bz.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bz.pms.model.ProductVO;
import com.bz.pms.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductConfiguration {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductVO> createProduct(@RequestBody ProductVO product) {
		ProductVO createdProduct = productService.createProduct(product);
		
		return new ResponseEntity<>(createdProduct, HttpStatus.OK);
	}
	
}
