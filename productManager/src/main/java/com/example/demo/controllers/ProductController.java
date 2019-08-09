package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	//ProductRepository repo;
	
	@Autowired
	List<Product> productList;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productList;
	}
	
	private Product filterProductById(long pid ) {
		return this.productList.stream()
				.filter(eachProduct-> eachProduct.getProductId()==pid).findFirst().orElse(null);
		
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") long pid) {
		
		return this.productList.stream()
			.filter(eachProduct-> eachProduct.getProductId()==pid).findFirst().orElse(null);
		
	}
	
	@PostMapping(value="/products", consumes="application/json", produces="application/json")
	public Product addProduct(@RequestBody Product product) {
		
		productList.add(product);
		return product;
		
	}
	
	@PutMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public Product updateProduct(@RequestBody Product product) {
		
		long productId = product.getProductId();	
		
		Product productToUpdate = filterProductById(productId);
		
		if(productToUpdate!=null) {
			productToUpdate.setProductId(product.getProductId());
			productToUpdate.setProductName(product.getProductName());
			productToUpdate.setRating(product.getRating());
			productToUpdate.setRefImage(product.getRefImage());
			productToUpdate.setPrice(product.getPrice());
		}
		return product;
	}
	
	@DeleteMapping(value="/products", consumes="application/json", produces="application/json")
	public Product removeProduct(@RequestBody Product product) {
		
		Product productToDelete = filterProductById(product.getProductId());
		
		if(productToDelete!=null) {
			
			this.productList.remove(productToDelete);
		}
		return productToDelete;
		
	}
	
}
