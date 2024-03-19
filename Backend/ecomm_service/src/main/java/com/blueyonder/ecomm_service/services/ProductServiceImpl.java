package com.blueyonder.ecomm_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
import com.blueyonder.ecomm_service.repositories.CategoryRepository;
import com.blueyonder.ecomm_service.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	private CategoryRepository categoryrepo;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getAllProductByName(String name) {
		// TODO Auto-generated method stub;
		return productRepository.findAllByProductName(name);
	}

	@Override
	public void deleteProduct(Integer prodId) {
		// TODO Auto-generated method stub
		productRepository.deleteById(prodId);
		
	}
	
	@Override
	public Optional<Product> getProductById(Integer prodId) {
		// TODO Auto-generated method stub
		Optional<Product> prod = productRepository.findById(prodId);
		return prod;
	}

	@Override
	public List<Product> getAllProductByCategory(Category category) {
		// TODO Auto-generated method stub
		return productRepository.findAllByCategory(category);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
	@Override
	public Product updateProductById(Integer id) {
		Optional<Product> prod = productRepository.findById(id);
		return productRepository.save(prod);
	}

}
