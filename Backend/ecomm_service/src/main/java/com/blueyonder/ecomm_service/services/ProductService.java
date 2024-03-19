package com.blueyonder.ecomm_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
import com.blueyonder.ecomm_service.exceptions.ProductNotFoundException;

public interface ProductService {
	public Product addProduct(Product product);
	public List<Product> getAllProducts();
	public Optional<Product> getAllProductByName(String name);
	public void deleteProduct(Integer prodId) throws ProductNotFoundException;
	public Optional<Product> getProductById(Integer prodId) throws ProductNotFoundException;
	public List<Product> getAllProductByCategory(Category category) throws ProductNotFoundException;
	public Product updateProductById(Integer id);
	

}
