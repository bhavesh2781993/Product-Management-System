package com.bz.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bz.pms.model.ProductVO;
import com.bz.pms.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductVO> createProduct(@RequestBody ProductVO product) {
		ProductVO createdProduct = productService.createProduct(product);
		
		return new ResponseEntity<>(createdProduct, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductVO>> fetchAllProducts() {
		List<ProductVO> fetchedProducts = productService.fetchProducts();
		
		return new ResponseEntity<>(fetchedProducts, HttpStatus.OK);
	}
	
	@GetMapping("/{product}/{company}")
	public ResponseEntity<ProductVO> fetchProductByNameAndCompany(
			@PathVariable("product") String product,
			@PathVariable("company") String company) {
		ProductVO fetchedProduct = productService.fetchProduct(product, company);
		
		return new ResponseEntity<>(fetchedProduct, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ProductVO> updateProduct(@RequestBody ProductVO product) {
		ProductVO updatedProduct = productService.updateProduct(product);
		
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteProduct(@RequestBody ProductVO product) {
		productService.deleteProduct(product);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
}
