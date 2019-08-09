package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Product;

@Configuration
public class AppConfig {
	
	@Bean
	public Product laptop() {
		return new Product(101, "Dell Laptop", "images/dell.jpg", 35000.00D, 4.2);
	}
	
	@Bean
	public Product mobilePhone() {
		return new Product(102, "Mobile company", "images/mobile.jpg", 1500.00D, 3.9);
	}
	
	@Bean
	public Product printer() {
		return new Product(103, "HP printer", "images/printer.jpg", 8000.00D, 4.0);
	}
}
